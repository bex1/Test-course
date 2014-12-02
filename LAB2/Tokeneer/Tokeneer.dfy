class EnrolmentStation {
	var users : set<User>;
	
	function HIGH(): int {3} // Constant are functions that always return the same value
	function MEDIUM(): int {2}
	function LOW(): int {1}
	
}

class User{
	var token : Token;
}

class Token {
	var fingerprint : int;
	var clearanceLevel : int;
}

class Door {
	var requiredClearanceLevel : int;
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