Native Minecraft Version:

-   1.19

Tested Minecraft Versions:

-   1.7
-   1.8
-   1.9
-   1.10
-   1.11
-   1.12
-   1.13
-   1.14
-   1.15
-   1.16
-   1.17
-   1.18
-   1.19

Languages Supported:

English, German

-   **Fast Performance**
-   **UUID Support**
-   **MySQL**
-   **Caches Players**
-   **Asynchronous tasks**
-   **Commands can enable or disable!**
-   **Support of**
    -   **Played Games**
    -   **Wins**
    -   **Kills**
    -   **Deaths**
    -   **K/D**
    -   **Skillpoints**
    -   **Open Chests**
    -   **Broken Blocks**
    -   **Placed Blocks**

​

-   **Any Ideas?**
-   **UUID <enable/disable> in config.yml**
-   **Top10 Wall - Addon (85%)**
-   **/stats with messages.yml  ✓**
-   **/stats <enable/disable> in messages.yml  **✓****
-   **add new Stats**
    -   **Skillpoints**
    -   **coins**
    -   ****distance traveled****
    -   ******times you have eaten******
    -   ********and more..********

-   **/stats**
-   **/stats (player)**
  

Spoiler:  database.yml


    DATABASE:  
	HOST:  locahost  
	PORT:  '3306'  
	USER:  username  
	PASSWORD:  password  
	DATABASE:  database  

  

Spoiler:  messages.yml


	MESSAGE:  
	STATS:  
	PREFIX: '&8[&7StatsAPI&8]'  
	NOSTATS: '%PREFIX% &cNo Stats available'  
	NOPLAYER: '%PREFIX% &cNo Player with this Minecraftname exists'  
	ONKOWNCOMMAND: '%PREFIX% &cuse: &e/stats [player]'  
	COMMAND:  
	- '&7-=-=] &eStats %PLAYERNAME% &7[=-=-'  
	- ' &7Games: &e%GAMES%'  
	- ' &7Wins: &e%WINS%'  
	- ' &7Kills: &e%KILLS%'  
	- ' &7Deaths: &e%DEATHS%'  
	- ' &7K/D: &e%KD%'  
	- '&7-=-=-=-=-=-=-=-=-=-=-=-'  

  

Spoiler:  Config.yml

	CONFIG:  
		COMMAND:  
			STATS:  true

  ## Codes and Usages
	
	public  StatsManager statsManager;  
	
	PlayerStats stats  =  statsManager.getPlayerStats(event.getPlayer().getUniqueId());  
  
	int  kills  =  stats.getKills();  
	int  deaths  =  stats.getDeaths();  
	int  games  =  stats.getGames();  
	int  wins  =  stats.getWins();  
  

	double  kd  =  stats.getKD();  
	stats.addKills(int  kills);  
	stats.addDeaths(int  deaths);  
	stats.addWins(int  wins);  
	stats.addGames(int  games);  
	stats.setKills(int  kills);  
	stats.setDeaths(int  deaths);  
	stats.setWins(int  wins);  
	stats.setGames(int  games);  
	stats.addOpenChests(int  chests);  
	stats.addPlacedblocks(int  blocks);  
	stats.addBrokenblocks(int  blocks);  
	stats.setOpenChests(int  chests);  
	stats.setPlacedblocks(int  blocks);  
	stats.setBrokenblocks(int  blocks);  
	stats.getBrokenblocks();  
	stats.getPlacedblocks();  
	stats.getOpenchests();  
  
	stats.addSkillPoints(int  points);  
	stats.removeSkillPoints(int  points);  
	stats.getSKillpoints();...  
  
	UUID  uuid  =  StatsAPI.getRankingManager().getUUID(RankedType,  1);  
	int  rank  =  StatsAPI.getRankingManager().getValues(RankedType, uuid);  
<<<<<<< HEAD
=======

>>>>>>> pr/2
