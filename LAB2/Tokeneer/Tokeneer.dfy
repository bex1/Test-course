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
	requires user.token.valid;
	ensures validateFingerPrint(user.token, scanFinger(user)) && validateClearanceLevel(user.token, door) ==> accessGranted;
	ensures validateFingerPrint(user.token, scanFinger(user)) ==> user.token.valid;
	ensures !validateFingerPrint(user.token, scanFinger(user)) ==> !accessGranted && !user.token.valid;
	ensures !validateClearanceLevel(user.token, door) ==> !accessGranted;
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
	
	method Main()
	{
		var clearanceLevelHigh := 3;
		var token1 := new Token.Init(1, clearanceLevelHigh);
		var door1 := new Door.Init(clearanceLevelHigh);
		var user1 := new User.Init(1, token1);
		var enrolmentStation1 := new EnrolmentStation;
		var accessGranted := enrolmentStation1.enterDoor(user1, door1);
		if (accessGranted)
		{
			print "Access granted";
		}
		else
		{
			print "Access denied";
		}
	}
}
