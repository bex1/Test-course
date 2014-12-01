class EnrolmentStation {
	var users : set<User>;

	// Dunno where to place these. And how do one specify constants?
	var HIGH  : int;
	var MEDIUM : int;
	var LOW : int;
	
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