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
}

class Door { // Aka ID Station
	var requiredClearanceLevel : int;
	var alarmOn : bool;
	
	method Init(requiredClearanceLevel : int)
	modifies this;
	requires requiredClearanceLevel == HIGH() || requiredClearanceLevel == MEDIUM() || requiredClearanceLevel == LOW();
	ensures this.requiredClearanceLevel == requiredClearanceLevel && !alarmOn;
	{
		this.requiredClearanceLevel := requiredClearanceLevel;
		alarmOn := false;
	}
	
	method EnterDoor(user : User, fingerPrint : int) returns (accessGranted : bool)
	modifies user.token`valid, `alarmOn;
	requires user != null && user.token != null;
	ensures !old(user.token.valid) || !ValidateFingerPrint(user.token, fingerPrint) ==> !accessGranted && !user.token.valid && alarmOn;
	ensures old(user.token.valid) && ValidateFingerPrint(user.token, fingerPrint) && ValidateClearanceLevel(user.token) ==> accessGranted && user.token.valid && !alarmOn;
	ensures old(user.token.valid) && ValidateFingerPrint(user.token, fingerPrint) && !ValidateClearanceLevel(user.token) ==> !accessGranted && user.token.valid && !alarmOn;
	{
		if (user.token.valid)
		{
			var validFingerPrint := ValidateFingerPrint(user.token, fingerPrint);
			if (!validFingerPrint)
			{
				user.token.valid := false;
				alarmOn := true;
				accessGranted := false;
				return;
			}
			accessGranted := ValidateClearanceLevel(user.token);
			alarmOn := false;
		}
		else
		{
			accessGranted := false;
			alarmOn := true;
		}
	}
	
	function method ValidateClearanceLevel(token : Token) : bool
	reads token, this;
	requires token != null;
	{
		token.clearanceLevel >= requiredClearanceLevel
	}

	function method ValidateFingerPrint(token : Token, scannedFingerprint : int) : bool
	reads token;
	requires token != null;
	{
		token.fingerprint == scannedFingerprint
	}
}

class User {
	var token : Token;
	
	method Init()
	modifies this;
	ensures this.token == null;
	{
		this.token := null;
	}

}

class EnrolmentStation {
	var users : set<User>;

	method Init()
	modifies this;
	ensures users == {};
	ensures fresh(users);
	{
		users := {};
	}
	
	method Enrol(user : User, clearanceLevel : int, fingerPrint : int) 
	modifies this, user`token, user.token;
	requires user != null && user.token == null;
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

method Main()
{
		// Enrolment station
		var enrolmentStation := new EnrolmentStation.Init();

		// Doors
		var doorHigh := new Door.Init(HIGH());
		var doorMedium := new Door.Init(MEDIUM());
		var doorLow := new Door.Init(LOW());

		// Users
		var userHigh1 := new User.Init();
	    var userHigh2 := new User.Init();
		var userMedium3 := new User.Init();
	    var userMedium4 := new User.Init();
	    var userLow5 := new User.Init();
	    var userLow6 := new User.Init();


		// ******* Enrolment tests *******
		enrolmentStation.Enrol(userHigh1, HIGH(), 1);

		assert enrolmentStation.users == {userHigh1};
		assert userHigh1.token.clearanceLevel == HIGH();
		assert userHigh1.token.fingerprint == 1;
		assert userHigh1.token.valid;

		enrolmentStation.Enrol(userHigh2, HIGH(), 2);

		assert enrolmentStation.users == {userHigh1, userHigh2};
		assert userHigh2.token.clearanceLevel == HIGH();
		assert userHigh2.token.fingerprint == 2;
		assert userHigh2.token.valid;

		enrolmentStation.Enrol(userMedium3, MEDIUM(), 3);

		assert enrolmentStation.users == {userHigh1, userHigh2, userMedium3};
		assert userMedium3.token.clearanceLevel == MEDIUM();
		assert userMedium3.token.fingerprint == 3;
		assert userMedium3.token.valid;

		enrolmentStation.Enrol(userMedium4, MEDIUM(), 4);

		assert enrolmentStation.users == {userHigh1, userHigh2, userMedium3, userMedium4};
		assert userMedium4.token.clearanceLevel == MEDIUM();
		assert userMedium4.token.fingerprint == 4;
		assert userMedium4.token.valid;

		enrolmentStation.Enrol(userLow5, LOW(), 5);

		assert enrolmentStation.users == {userHigh1, userHigh2, userMedium3, userMedium4, userLow5};
		assert userLow5.token.clearanceLevel == LOW();
		assert userLow5.token.fingerprint == 5;
		assert userLow5.token.valid;

		enrolmentStation.Enrol(userLow6, LOW(), 6);

		assert enrolmentStation.users == {userHigh1, userHigh2, userMedium3, userMedium4, userLow5, userLow6};
		assert userLow6.token.clearanceLevel == LOW();
		assert userLow6.token.fingerprint == 6;
		assert userLow6.token.valid;



		// ******** Enter door tests ********

		// ***Valid fingerprints***
		
		// High security door
		var accessGranted := doorHigh.EnterDoor(userHigh1, 1);
		assert userHigh1.token.valid;
		assert accessGranted;
		assert !doorHigh.alarmOn;

		accessGranted := doorHigh.EnterDoor(userMedium3, 3);
		assert userMedium3.token.valid;
		assert !accessGranted;
		assert !doorHigh.alarmOn;

		accessGranted := doorHigh.EnterDoor(userLow5, 5);
		assert userLow5.token.valid;
		assert !accessGranted;
		assert !doorHigh.alarmOn;

		// Medium security door

		accessGranted := doorMedium.EnterDoor(userHigh1, 1);
		assert userHigh1.token.valid;
		assert accessGranted;
		assert !doorMedium.alarmOn;

		accessGranted := doorMedium.EnterDoor(userMedium3, 3);
		assert userMedium3.token.valid;
		assert accessGranted;
		assert !doorMedium.alarmOn;

		accessGranted := doorMedium.EnterDoor(userLow5, 5);
		assert userLow5.token.valid;
		assert !accessGranted;
		assert !doorMedium.alarmOn;

		// Low security door

		accessGranted := doorLow.EnterDoor(userHigh1, 1);
		assert userHigh1.token.valid;
		assert accessGranted;
		assert !doorLow.alarmOn;

		accessGranted := doorLow.EnterDoor(userMedium3, 3);
		assert userMedium3.token.valid;
		assert accessGranted;
		assert !doorLow.alarmOn;

		accessGranted := doorLow.EnterDoor(userLow5, 5);
		assert userLow5.token.valid;
		assert accessGranted;
		assert !doorLow.alarmOn;



		// ***Invalid fingerprints***
		
		// High security door
		accessGranted := doorHigh.EnterDoor(userHigh2, 1);
		assert !userHigh2.token.valid;
		assert !accessGranted;
		assert doorHigh.alarmOn;

		accessGranted := doorHigh.EnterDoor(userMedium4, 3);
		assert !userMedium4.token.valid;
		assert !accessGranted;
		assert doorHigh.alarmOn;

		accessGranted := doorHigh.EnterDoor(userLow6, 5);
		assert !userLow6.token.valid;
		assert !accessGranted;
		assert doorHigh.alarmOn;

		// Medium security door

		accessGranted := doorMedium.EnterDoor(userHigh2, 1);
		assert !userHigh2.token.valid;
		assert !accessGranted;
		assert doorMedium.alarmOn;

		accessGranted := doorMedium.EnterDoor(userMedium4, 3);
		assert !userMedium4.token.valid;
		assert !accessGranted;
		assert doorMedium.alarmOn;

		accessGranted := doorMedium.EnterDoor(userLow6, 5);
		assert !userLow6.token.valid;
		assert !accessGranted;
		assert doorMedium.alarmOn;

		// Low security door

		accessGranted := doorLow.EnterDoor(userHigh2, 1);
		assert !userHigh2.token.valid;
		assert !accessGranted;
		assert doorLow.alarmOn;

		accessGranted := doorLow.EnterDoor(userMedium4, 3);
		assert !userMedium4.token.valid;
		assert !accessGranted;
		assert doorLow.alarmOn;

		accessGranted := doorLow.EnterDoor(userLow6, 5);
		assert !userLow6.token.valid;
		assert !accessGranted;
		assert doorLow.alarmOn;
}
