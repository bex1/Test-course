/*
While the real system has a very large specification, this question asks you to model a 
high-level mini-version of the Tokeneer system in Dafny and verify its correctness. 

The picture below shows the secure door system you are to model and verify. 

To enter the door,
*********** the user first needs to enrol and receive a token <--- TODO (1) ******************, which stores fingerprint data (for 
 simplicity, you may assume fingerprints are represented by integers) and the security clearance 
 level (there are three clearance levels: Low, Medium and High). 

 ************* The enrolment station keeps track of the users in the system to make sure no one can be issued with more than one token at the time. <--- TODO (2) *******************

When a user wants to open a door, the ID station checks that the user's fingerprint agrees with 
what is stored on the user's token and that the user have the adequate security clearance to enter 
this door. 

If a security breach is discovered, i.e. a token is used by the wrong person, this token 
must be invalidated immediately and ************* the alarm sound. (3) <--- TODO ***********
*/

// Defined clearance levels
static function method HIGH(): int {3} 
static function method MEDIUM(): int {2}
static function method LOW(): int {1}

class Token {
	var fingerprint : int;
	var clearanceLevel : int;
	var valid : bool;
	
	method Init(fingerprint : int, clearanceLevel : int)
	modifies this;
	requires clearanceLevel == HIGH() || clearanceLevel == MEDIUM() || clearanceLevel == LOW();
	ensures this.fingerprint == fingerprint && this.clearanceLevel == clearanceLevel && valid == true;
	{
		this.fingerprint := fingerprint;
		this.clearanceLevel := clearanceLevel;
		valid := true;
	}
	
	function method ValidateFingerPrint(scannedFingerprint : int) : bool
	reads this;
	{
		fingerprint == scannedFingerprint
	}
}

class Door { // Aka ID Station
	var requiredClearanceLevel : int;
	
	method Init(requiredClearanceLevel : int)
	modifies this;
	requires requiredClearanceLevel == HIGH() || requiredClearanceLevel == MEDIUM() || requiredClearanceLevel == LOW();
	ensures this.requiredClearanceLevel == requiredClearanceLevel;
	{
		this.requiredClearanceLevel := requiredClearanceLevel;
	}
	
	method EnterDoor(user : User) returns (accessGranted : bool)
	modifies user.token`valid;
	requires user != null && user.token != null;
	ensures !old(user.token.valid) || !user.token.ValidateFingerPrint(user.ScanFinger()) ==> !accessGranted && !user.token.valid;
	ensures old(user.token.valid) && user.token.ValidateFingerPrint(user.ScanFinger()) && ValidateClearanceLevel(user.token) ==> accessGranted && user.token.valid;
	ensures old(user.token.valid) && user.token.ValidateFingerPrint(user.ScanFinger()) && !ValidateClearanceLevel(user.token) ==> !accessGranted && user.token.valid;
	{
		if (user.token.valid)
		{
			var validFingerPrint := user.token.ValidateFingerPrint(user.ScanFinger());
			if (!validFingerPrint)
			{
				user.token.valid := false;
				accessGranted := false;
				return;
			}
			accessGranted := ValidateClearanceLevel(user.token);
		}
		else
		{
			accessGranted := false;
		}
	}
	
	function method ValidateClearanceLevel(token : Token) : bool
	reads token, this;
	requires token != null;
	{
		token.clearanceLevel >= requiredClearanceLevel
	}
}

class User {
	var token : Token;
	var fingerprint : int;
	
	method Init(fingerprint : int)
	modifies this;
	ensures this.fingerprint == fingerprint;
	ensures this.token == null;
	{
		this.fingerprint := fingerprint;
		this.token := null;
	}
	
	function method ScanFinger() : int
	reads this;
	{
		fingerprint
	}
}

class EnrolmentStation {
	var users : set<User>; //behövs denna?? Svar: Ja, för enrolment. kolla (1) och (2) i specifikationen överst 

	method Init()
	modifies this;
	ensures users == {};
	ensures fresh(users);
	{
		users := {};
	}
	
	// TODO använd denna i tester! löser både (1) och (2) i specc.
	method Enrol(user : User, clearanceLevel : int, fingerPrint : int) 
	modifies this, user`token, user.token;
	requires user != null && user.token == null;
	requires user.fingerprint == fingerPrint;
	requires clearanceLevel == HIGH() || clearanceLevel == MEDIUM() || clearanceLevel == LOW();
	requires user !in users; 
	ensures users == old(users) + {user}; 
	ensures user.token != null; 
	ensures user.token.fingerprint == fingerPrint;
	ensures user.token.clearanceLevel == clearanceLevel;
	ensures user.token.valid == true;
	ensures fresh(user.token);
	{
		user.token := new Token.Init(fingerPrint, clearanceLevel);
		users := users + {user};
	}
}

method PrintAccess(accessGranted : bool, tokenValid : bool)
{
	if (accessGranted)
		{
			print "Access granted!\n\n";
		}
		else
		{
			print "Access denied!\n";
		if (!tokenValid)
		{
			print "Token invalid!\n\n";
		}
		else
		{
			print "\n";
		}
	}
}

method Main() // TODO ska nog använda asserts här istället för prints som i excercises
{
		// Enrolment station
		var enrolmentStation := new EnrolmentStation.Init();

		// Doors
		var doorHigh := new Door.Init(HIGH());
		var doorMedium := new Door.Init(MEDIUM());
		var doorLow := new Door.Init(LOW());

		// Users
		var userHigh1 := new User.Init(1);
	    var userHigh2 := new User.Init(2);
		var userMedium3 := new User.Init(3);
	    var userMedium4 := new User.Init(4);
	    var userLow5 := new User.Init(5);
	    var userLow6 := new User.Init(6);


		// Enrolment tests
		enrolmentStation.Enrol(userHigh1, HIGH(), 1);

		assert enrolmentStation.users == {userHigh1};
		assert userHigh1.token.clearanceLevel == HIGH();
		assert userHigh1.token.fingerprint == 1;

		enrolmentStation.Enrol(userHigh2, HIGH(), 2);

		assert enrolmentStation.users == {userHigh1, userHigh2};
		assert userHigh2.token.clearanceLevel == HIGH();
		assert userHigh2.token.fingerprint == 2;

		enrolmentStation.Enrol(userMedium3, MEDIUM(), 3);

		assert enrolmentStation.users == {userHigh1, userHigh2, userMedium3};
		assert userMedium3.token.clearanceLevel == MEDIUM();
		assert userMedium3.token.fingerprint == 3;

		enrolmentStation.Enrol(userMedium4, MEDIUM(), 4);

		assert enrolmentStation.users == {userHigh1, userHigh2, userMedium3, userMedium4};
		assert userMedium4.token.clearanceLevel == MEDIUM();
		assert userMedium4.token.fingerprint == 4;

		enrolmentStation.Enrol(userLow5, LOW(), 5);

		assert enrolmentStation.users == {userHigh1, userHigh2, userMedium3, userMedium4, userLow5};
		assert userLow5.token.clearanceLevel == LOW();
		assert userLow5.token.fingerprint == 5;

		enrolmentStation.Enrol(userLow6, LOW(), 6);

		assert enrolmentStation.users == {userHigh1, userHigh2, userMedium3, userMedium4, userLow5, userLow6};
		assert userLow6.token.clearanceLevel == LOW();
		assert userLow6.token.fingerprint == 6;

		// Enter door tests
		var accessGranted := doorHigh.EnterDoor(userHigh1);
		assert userHigh1.token.valid;
		assert accessGranted;

		accessGranted := doorHigh.EnterDoor(userHigh2);
		assert userHigh2.token.valid;
		assert accessGranted;

		accessGranted := doorHigh.EnterDoor(userMedium3);
		assert userMedium3.token.valid;
		assert !accessGranted;

		accessGranted := doorHigh.EnterDoor(userMedium4);
		assert userMedium4.token.valid;
		assert !accessGranted;

		accessGranted := doorHigh.EnterDoor(userLow5);
		assert userLow5.token.valid;
		assert !accessGranted;

		accessGranted := doorHigh.EnterDoor(userLow6);
		assert userLow6.token.valid;
		assert !accessGranted;


		/*
		var user1 := new User.Init(1, token1);
		var accessGranted := door1.EnterDoor(user1);
		print "user1 with correct fingerprint tries to access door1 with token1 (Clearance level enough):\n";
		PrintAccess(accessGranted, token1.valid);
		
		var user2 := new User.Init(2, token1);
		accessGranted := door1.EnterDoor(user2);
		print "user2 with wrong fingerprint tries to access door1 with token1 (Clearance level enough):\n";
		PrintAccess(accessGranted, token1.valid);

		var user3 := new User.Init(1, token1);
		accessGranted := door1.EnterDoor(user3);
		print "user3 with correct fingerprint tries to access door1 with token1 again with invalid token (Clearance level enough):\n";
		PrintAccess(accessGranted, token1.valid);
		
		var token2 := new Token.Init(1, clearanceLevelMedium);
		
		var user4 := new User.Init(1, token2);
		accessGranted := door1.EnterDoor(user4);
		print "user4 with correct fingerprint tries to access door1 with token2 (Clearance level to low):\n";
		PrintAccess(accessGranted, token2.valid);
		
		var door2 := new Door.Init(clearanceLevelMedium);
		accessGranted := door2.EnterDoor(user4);
		print "user4 with correct fingerprint tries to access door2 with token2 (Clearance level enough):\n";
		PrintAccess(accessGranted, token2.valid);
		
		var door3 := new Door.Init(clearanceLevelLow);
		accessGranted := door3.EnterDoor(user4);
		print "user4 with correct fingerprint tries to access door3 with token2 (Clearance level more than needed):\n";
		PrintAccess(accessGranted, token2.valid);*/
}
