class Token {
	var fingerprint : int;
	var clearanceLevel : int;
	var valid : bool;
	
	method Init(fingerprintData : int, clearanceLevelData : int)
	modifies this;
	ensures fingerprint == fingerprintData && clearanceLevel == clearanceLevelData && valid == true;
	{
		fingerprint := fingerprintData;
		clearanceLevel := clearanceLevelData;
		valid := true;
	}
}

class User{
	var token : Token;
	var fingerprint : int;
	
	method Init(token: Token, fingerprint : int)
	modifies this;
	requires token != null;
	ensures this.token == token && this.fingerprint == fingerprint;
	{
		this.token := token;
		this.fingerprint := fingerprint;
	}
	
}



class Door {
	var requiredClearanceLevel : int;
	
	method Init(requiredClearanceLevel : int)
	modifies this;
	ensures this.requiredClearanceLevel == requiredClearanceLevel;
	{
		this.requiredClearanceLevel := requiredClearanceLevel;
	}
}

class EnrolmentStation {
	var users : set<User>;
	
	function HIGH(): int {3} // Constant are functions that always return the same value
	function MEDIUM(): int {2}
	function LOW(): int {1}
	
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
	
	/*
	method validateFingerPrint(token : Token, scannedFingerprint : int) returns (validFingerPrint : bool)
	modifies token`valid;
	requires token != null;
	ensures token.fingerprint == scannedFingerprint ==> validFingerPrint;
	ensures token.fingerprint != scannedFingerprint ==> !token.valid && !validFingerPrint;
	{
		if (token.fingerprint == scannedFingerprint)
		{
			validFingerPrint := true;
		}
		else
		{
			token.valid := false;
			validFingerPrint := false;
		}
	}
	*/
	/*
	method validateClearanceLevel(token : Token, door : Door) returns (validClearanceLevel : bool)
	requires token != null;
	requires door != null;
	ensures token.clearanceLevel >= door.requiredClearanceLevel ==> validClearanceLevel;
	ensures token.clearanceLevel < door.requiredClearanceLevel ==> !validClearanceLevel;
	{
		validClearanceLevel := token.clearanceLevel >= door.requiredClearanceLevel;
	}
	*/
}



/*
method Init:
1. Skapa en användare och tilldela en fingerprint data
2. User får en token, token.valid := true

method EnterDoor(token : Token, door : Door) : bool (true om man får komma in, false annars)
	method scanFinger : returns (fingerprint : int) 4. skanna fingerprint. skannad.fingerprint == token.fingerprint
	method validateFingerPrint(token : Token, scannedFingerprint: int) : bool (true om valid, false annars)
	{
		if (token.fingerprint == scannedFingerprint)
			{
				return true;
			}
			else
			{
				token.valid := false;
				return false;
			}
	}
	if (!validateFingerPrint(token,...)
	{
		return false;
	}
	method validateClearanceLevel(token : Token, door : Door) : bool
		if (token.clearanceLevel >= door.requiredClearanceLevel)
			{
				
			}
			else
			{
				
			}
5. 
6. if okay: print access granted, open door
7. else: print access denied, door not opened
*/