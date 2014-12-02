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
	
	function method scanFinger(user : User) : int
	reads user;
	requires user != null;
	{
		user.fingerprint
	}
	
	function method validateFingerPrint(token : Token, scannedFingerprint : int) : bool
	reads token;
	requires token != null;
	{
		token.fingerprint == scannedFingerprint
	}
	
	function method validateClearanceLevel(token : Token, door : Door) : bool
	reads token, door;
	requires token != null;
	requires door != null;
	{
		token.clearanceLevel >= door.requiredClearanceLevel
	}
  
	method enterDoor(user : User, door : Door) returns (accessGranted : bool)
	modifies user.token`valid;
	requires user != null && user.token != null && door != null;
	ensures user.token.valid && validateFingerPrint(user.token, scanFinger(user)) && validateClearanceLevel(user.token, door) ==> accessGranted && user.token.valid;
	ensures !user.token.valid || !validateFingerPrint(user.token, scanFinger(user)) ==> !accessGranted && !user.token.valid;
	ensures !user.token.valid || !validateClearanceLevel(user.token, door) ==> !accessGranted;
	{
		if (user.token.valid)
		{
			var validFingerPrint := validateFingerPrint(user.token, scanFinger(user));
			if (!validFingerPrint)
			{
				user.token.valid := false;
				accessGranted := false;
				return;
			}
			accessGranted := validateClearanceLevel(user.token, door);
		}
		else
		{
			accessGranted := false;
		}
	}
	
	method printAccess(accessGranted : bool, tokenValid : bool)
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
		}
	}
	
	method main()
	{
		var clearanceLevelHigh := 3;
		var clearanceLevelMedium := 2;
		var clearanceLevelLow := 1;
		
		var token1 := new Token.Init(1, clearanceLevelHigh);
		var door1 := new Door.Init(clearanceLevelHigh);
		var enrolmentStation := new EnrolmentStation;
		
		var user1 := new User.Init(1, token1);
			var accessGranted := enrolmentStation.enterDoor(user1, door1);
		print "user1 with right fingerprint tries to access door1 with token1 (Clearance level enough):\n";
		printAccess(accessGranted, token1.valid);
		
		var user2 := new User.Init(2, token1);
		accessGranted := enrolmentStation.enterDoor(user2, door1);
		print "user2 with wrong fingerprint tries to access door1 with token1 (Clearance level enough):\n";
		printAccess(accessGranted, token1.valid);
		
		var user3 := new User.Init(1, token1);
		accessGranted := enrolmentStation.enterDoor(user2, door1);
		print "user1 with right fingerprint tries to access door1 with token1 (Clearance level enough) again:\n";
		printAccess(accessGranted, token1.valid);
	
	}
}
