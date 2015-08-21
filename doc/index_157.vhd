--------------------------------------------
-- généré par LILASV4                     --
--------------------------------------------
library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.Numeric_Std.all;
use IEEE.std_logic_unsigned.all;

entity tst_code_logic_Memoire is
port(
	adr : IN std_logic_vector(3 downto 0) := "UUUU";
	we : IN std_logic := 'U';
	clk : IN std_logic := 'U';
	din : IN std_logic_vector(7 downto 0) := "UUUUUUUU";
	dout : OUT std_logic_vector(7 downto 0) := "UUUUUUUU");
end entity tst_code_logic_Memoire;

architecture a_tst_code_logic_Memoire of tst_code_logic_Memoire is
-- déclaration des variables modules 
-- déclaration des signaux internes 
	type mem_type is array (0 to 16-1) of std_logic_vector(8-1 downto 0);
	signal mem : mem_type := (
		0 => "11111111", 
		1 => "11111110", 
		2 => "11111101", 
		3 => "11111100", 
		4 => "11111011", 
		5 => "11111010", 
		6 => "11111001", 
		7 => "11111000", 
		8 => "11110111", 
		9 => "11110110", 
		10 => "11110101", 
		11 => "11110100", 
		12 => "11110011", 
		13 => "11110010", 
		14 => "11110001", 
		15 => "11110000", 
		others => (others => 'U'));
-- déclaration des variables locales 
	signal ldl1375940957581 :  std_logic  := '1';
begin
	process (clk, we, adr)
	begin
		if (clk'EVENT and clk='1' and we=ldl1375940957581) then 
						mem(conv_integer(adr)) <= din;
		end if;
		dout <= mem(conv_integer(adr));
	end process;

end architecture a_tst_code_logic_Memoire;

