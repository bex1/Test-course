/*
 * Daniel Bäckström and Martin Hermansson
 * Group 4
 */

method ComputeFact(n : nat) returns (res : nat)
requires n > 0;
ensures res == fact(n);
{
	res := 1;
	var i := 2;
	while (i <= n)
	invariant (i <= n + 1) && res == fact(i - 1);
	decreases (n - i + 1);
	{
		res := res * i;
		i := i + 1;
	}
}

function fact(m : nat) : nat
requires m > 0;
{
	if (m == 1) then 1 else m * fact(m - 1)
}