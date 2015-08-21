--------------------------------------------
-- généré par LILASV4                     --
--------------------------------------------
library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.Numeric_Std.all;
use IEEE.std_logic_unsigned.all;

entity tst_code_logic_LocalInternal is
port(
	a : IN std_logic := '0';
	b : IN std_logic := '0';
	cin : IN std_logic := '0';
	cout : OUT std_logic := '0';
	s : OUT std_logic := '0');
end entity tst_code_logic_LocalInternal;

architecture a_tst_code_logic_LocalInternal of tst_code_logic_LocalInternal is
-- déclaration des variables modules 
-- déclaration des signaux internes 
	signal p :  std_logic  := 'U';
-- déclaration des variables locales 
begin
	process (a, b, p, cin)
		variable g :  std_logic;
	begin
		g := (a and b);
		p <= (a or b);
		cout <= (g or (p and cin));
		s <= (a xor b xor cin);
	end process;

end architecture a_tst_code_logic_LocalInternal;

