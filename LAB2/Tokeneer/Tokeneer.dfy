class Token {
	var fingerprint : int;
	var clearanceLevel : int;
	var valid : bool;
	
	method Init(fingerprint : int, clearanceLevel : int)
	modifies this;
	ensures this.fingerprint == fingerprint && this.clearanceLevel == clearanceLevel && valid == true;
	{
		this.fingerprint := fingerprint;
		this.clearanceLevel := clearanceLevel;
		valid := true;
	}
}

class Door {
	var requiredClearanceLevel : int;

	static function method HIGH(): int {3} // Constant are functions that always return the same value
	static function method MEDIUM(): int {2}
	static function method LOW(): int {1}
	
	method Init(requiredClearanceLevel : int)
	modifies this;
	ensures this.requiredClearanceLevel == requiredClearanceLevel;
	{
		this.requiredClearanceLevel := requiredClearanceLevel;
	}
}

class User {
	var token : Token;
	var fingerprint : int;
	
	method Init(fingerprint : int, token: Token)
	modifies this;
	requires token != null;
	ensures this.fingerprint == fingerprint && this.token == token;
	{
		this.fingerprint := fingerprint;
		this.token := token;
	}
}

class EnrolmentStation {
	var users : set<User>; //behövs denna??
	
	function method ScanFinger(user : User) : int
	reads user;
	requires user != null;
	{
		user.fingerprint
	}
	
	function method ValidateFingerPrint(token : Token, scannedFingerprint : int) : bool
	reads token;
	requires token != null;
	{
		token.fingerprint == scannedFingerprint
	}
	
	function method ValidateClearanceLevel(token : Token, door : Door) : bool
	reads token, door;
	requires token != null;
	requires door != null;
	{
		token.clearanceLevel >= door.requiredClearanceLevel
	}
  
	method EnterDoor(user : User, door : Door) returns (accessGranted : bool)
	modifies user.token`valid;
	requires user != null && user.token != null && door != null;
	ensures user.token.valid && ValidateFingerPrint(user.token, ScanFinger(user)) && ValidateClearanceLevel(user.token, door) ==> accessGranted && user.token.valid;
	ensures !user.token.valid ==> !accessGranted && !user.token.valid;
	ensures !ValidateFingerPrint(user.token, ScanFinger(user)) ==> !accessGranted && !user.token.valid;
	ensures !ValidateClearanceLevel(user.token, door) ==> !accessGranted;
	{
		if (user.token.valid)
		{
			var validFingerPrint := ValidateFingerPrint(user.token, ScanFinger(user));
			if (!validFingerPrint)
			{
				user.token.valid := false;
				accessGranted := false;
				return;
			}
			accessGranted := ValidateClearanceLevel(user.token, door);
		}
		else
		{
			accessGranted := false;
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
	
	method Main()
	{
		var clearanceLevelHigh := 3;
		var clearanceLevelMedium := 2;
		var clearanceLevelLow := 1;
		
		var token1 := new Token.Init(1, clearanceLevelHigh);
		var door1 := new Door.Init(clearanceLevelHigh);
		var enrolmentStation := new EnrolmentStation;
		
		var user1 := new User.Init(1, token1);
		var accessGranted := enrolmentStation.EnterDoor(user1, door1);
		print "user1 with right fingerprint tries to access door1 with token1 (Clearance level enough):\n";
		PrintAccess(accessGranted, token1.valid);
		
		var user2 := new User.Init(2, token1);
		accessGranted := enrolmentStation.EnterDoor(user2, door1);
		print "user2 with wrong fingerprint tries to access door1 with token1 (Clearance level enough):\n";
		PrintAccess(accessGranted, token1.valid);
		
		var user3 := new User.Init(1, token1);
		accessGranted := enrolmentStation.EnterDoor(user3, door1);
		print "user3 with right fingerprint tries to access door1 with token1 again with invalid token (Clearance level enough):\n";
		PrintAccess(accessGranted, token1.valid);
		
		var token2 := new Token.Init(1, clearanceLevelMedium);
		
		var user4 := new User.Init(1, token2);
		accessGranted := enrolmentStation.EnterDoor(user4, door1);
		print "user4 with right fingerprint tries to access door1 with token2 (Clearance level to low):\n";
		PrintAccess(accessGranted, token2.valid);
		
		var door2 := new Door.Init(clearanceLevelMedium);
		accessGranted := enrolmentStation.EnterDoor(user4, door2);
		print "user4 with right fingerprint tries to access door2 with token2 (Clearance level enough):\n";
		PrintAccess(accessGranted, token2.valid);
		
		var door3 := new Door.Init(clearanceLevelLow);
		accessGranted := enrolmentStation.EnterDoor(user4, door3);
		print "user4 with right fingerprint tries to access door3 with token2 (Clearance level more than needed):\n";
		PrintAccess(accessGranted, token2.valid);
	
	}
}
