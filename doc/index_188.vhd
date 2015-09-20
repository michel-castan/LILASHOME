--------------------------------------------
-- généré par LILASV4                     --
--------------------------------------------
library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.Numeric_Std.all;
use IEEE.std_logic_unsigned.all;

entity tst_code_logic_Moore is
port(
	clk : IN std_logic := 'U';
	rst : IN std_logic := 'U';
	monter : OUT std_logic := 'U';
	descendre : OUT std_logic := 'U';
	DS : IN std_logic := 'U';
	DI : IN std_logic := 'U';
	MS : IN std_logic := 'U';
	MI : IN std_logic := 'U';
	MA : IN std_logic := 'U';
	DA : IN std_logic := 'U');
end entity tst_code_logic_Moore;

architecture a_tst_code_logic_Moore of tst_code_logic_Moore is
-- déclaration des variables modules 
-- déclaration des signaux internes 
-- déclaration des variables locales 
	type typeEtat is (CabineArretDescente, CabineArretMontée, CabineEnMontée, CabineEnDescente);
	signal etatCourant : typeEtat := CabineArretDescente;
begin
	process (rst, clk)
	begin
	
		if (rst='1') then etatCourant <= CabineArretDescente;
					monter <= '0';
					descendre <= '0';
		elsif (clk'EVENT and clk='1') then
			case etatCourant is
				when CabineArretDescente => 
					if DS='1' then etatCourant <= CabineEnMontée;
						monter <= '1';
						descendre <= '0';
					elsif DI='1' then etatCourant <= CabineEnDescente;
						monter <= '0';
						descendre <= '1';
					end if;
				when CabineArretMontée => 
					if MS='1' then etatCourant <= CabineEnMontée;
						monter <= '1';
						descendre <= '0';
					elsif MI='1' then etatCourant <= CabineEnDescente;
						monter <= '0';
						descendre <= '1';
					end if;
				when CabineEnMontée => 
					if MA='1' then etatCourant <= CabineArretMontée;
						monter <= '0';
						descendre <= '0';
					end if;
				when CabineEnDescente => 
					if DA='1' then etatCourant <= CabineArretDescente;
						monter <= '0';
						descendre <= '0';
					end if;
			end case;
		end if;
		
	end process;

end architecture a_tst_code_logic_Moore;

