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