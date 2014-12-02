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
	
	function method ValidateFingerPrint(scannedFingerprint : int) : bool
	reads this;
	{
		fingerprint == scannedFingerprint
	}
}

class Door {
	var requiredClearanceLevel : int;

	static function method HIGH(): int {3} 
	static function method MEDIUM(): int {2}
	static function method LOW(): int {1}
	
	method Init(requiredClearanceLevel : int)
	modifies this;
	ensures this.requiredClearanceLevel == requiredClearanceLevel;
	{
		this.requiredClearanceLevel := requiredClearanceLevel;
	}
	
	method EnterDoor(user : User) returns (accessGranted : bool)
	modifies user.token`valid;
	requires user != null && user.token != null;
	ensures user.token.valid && user.token.ValidateFingerPrint(user.ScanFinger()) && ValidateClearanceLevel(user.token) ==> accessGranted && user.token.valid;
	ensures !user.token.valid ==> !accessGranted && !user.token.valid;
	ensures !user.token.ValidateFingerPrint(user.ScanFinger()) ==> !accessGranted && !user.token.valid;
	ensures !ValidateClearanceLevel(user.token) ==> !accessGranted;
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
	
	method Init(fingerprint : int, token: Token)
	modifies this;
	requires token != null;
	ensures this.fingerprint == fingerprint && this.token == token;
	{
		this.fingerprint := fingerprint;
		this.token := token;
	}
	
	function method ScanFinger() : int
	reads this;
	{
		fingerprint
	}
}

class EnrolmentStation {
	var users : set<User>; //behövs denna??
	
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
		PrintAccess(accessGranted, token2.valid);
	
	}
}
