package com.treyzania.mc.kkgenconf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.UnsafeValues;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator.ChunkData;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import com.avaje.ebean.config.ServerConfig;

@SuppressWarnings("deprecation")
public class KkServer implements Server {
	
	private ServerProperties props;
	
	private Logger log;
	
	private KkPluginManager pluginManager;
	private KkBukkitScheduler sched;
	private KkServicesManager servicesManager;
	
	public KkServer(Logger logger) {
		
		this.pluginManager = new KkPluginManager();
		this.sched = new KkBukkitScheduler();
		this.servicesManager = new KkServicesManager();
		
	}
	
	@Override
	public void sendPluginMessage(Plugin source, String channel, byte[] message) {
		
	}

	@Override
	public Set<String> getListeningPluginChannels() {
		return new HashSet<>();
	}

	@Override
	public String getName() {
		return "kkgenconf";
	}

	@Override
	public String getVersion() {
		return "";
	}

	@Override
	public String getBukkitVersion() {
		return "1.11";
	}

	@Override
	public Player[] _INVALID_getOnlinePlayers() {
		return new Player[0];
	}

	@Override
	public Collection<? extends Player> getOnlinePlayers() {
		return new ArrayList<>();
	}

	@Override
	public int getMaxPlayers() {
		return this.props.getMaxPlayers();
	}

	@Override
	public int getPort() {
		return this.props.getPort();
	}

	@Override
	public int getViewDistance() {
		return this.props.getViewDistance();
	}

	@Override
	public String getIp() {
		return this.props.getIp();
	}

	@Override
	public String getServerName() {
		return this.props.getServerName();
	}

	@Override
	public String getServerId() {
		return this.props.getServerId();
	}

	@Override
	public String getWorldType() {
		return "DEFAULT";
	}

	@Override
	public boolean getGenerateStructures() {
		return true;
	}

	@Override
	public boolean getAllowEnd() {
		return true;
	}

	@Override
	public boolean getAllowNether() {
		return true;
	}

	@Override
	public boolean hasWhitelist() {
		return this.props.hasWhitelist();
	}

	@Override
	public void setWhitelist(boolean value) {
		
	}

	@Override
	public Set<OfflinePlayer> getWhitelistedPlayers() {
		return new HashSet<>();
	}

	@Override
	public void reloadWhitelist() {
		
	}

	@Override
	public int broadcastMessage(String message) {
		return 0;
	}

	@Override
	public String getUpdateFolder() {
		return this.getUpdateFolderFile().getAbsolutePath();
	}

	@Override
	public File getUpdateFolderFile() {
		return new File("update");
	}

	@Override
	public long getConnectionThrottle() {
		return 0;
	}

	@Override
	public int getTicksPerAnimalSpawns() {
		return 0;
	}

	@Override
	public int getTicksPerMonsterSpawns() {
		return 0;
	}

	@Override
	public Player getPlayer(String name) {
		return new KkPlayer(name);
	}

	@Override
	public Player getPlayerExact(String name) {
		return new KkPlayer(name);
	}

	@Override
	public List<Player> matchPlayer(String name) {
		return Arrays.asList(new KkPlayer(name));
	}

	@Override
	public Player getPlayer(UUID id) {
		return new KkPlayer(id);
	}

	@Override
	public PluginManager getPluginManager() {
		return this.pluginManager;
	}

	@Override
	public BukkitScheduler getScheduler() {
		return this.sched;
	}

	@Override
	public ServicesManager getServicesManager() {
		return this.servicesManager;
	}

	@Override
	public List<World> getWorlds() {
		return new ArrayList<>();
	}

	@Override
	public World createWorld(WorldCreator creator) {
		return null;
	}

	@Override
	public boolean unloadWorld(String name, boolean save) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unloadWorld(World world, boolean save) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public World getWorld(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public World getWorld(UUID uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapView getMap(short id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapView createMap(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reload() {
		
	}

	@Override
	public Logger getLogger() {
		return this.log;
	}

	@Override
	public PluginCommand getPluginCommand(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePlayers() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void configureDbConfig(ServerConfig config) {
		
	}

	@Override
	public boolean addRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Recipe> getRecipesFor(ItemStack result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Recipe> recipeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearRecipes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetRecipes() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String[]> getCommandAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSpawnRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSpawnRadius(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getOnlineMode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getAllowFlight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHardcore() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public int broadcast(String message, String permission) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OfflinePlayer getOfflinePlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfflinePlayer getOfflinePlayer(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getIPBans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void banIP(String address) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unbanIP(String address) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<OfflinePlayer> getBannedPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BanList getBanList(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<OfflinePlayer> getOperators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameMode getDefaultGameMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultGameMode(GameMode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public ConsoleCommandSender getConsoleSender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getWorldContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfflinePlayer[] getOfflinePlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Messenger getMessenger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HelpMap getHelpMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder owner, InventoryType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder owner, int size) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder owner, int size, String title) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Merchant createMerchant(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMonsterSpawnLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAnimalSpawnLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWaterAnimalSpawnLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAmbientSpawnLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isPrimaryThread() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMotd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getShutdownMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WarningState getWarningState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemFactory getItemFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreboardManager getScoreboardManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CachedServerIcon getServerIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CachedServerIcon loadServerIcon(BufferedImage image) throws IllegalArgumentException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIdleTimeout(int threshold) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIdleTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ChunkData createChunkData(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnsafeValues getUnsafe() {
		return null;
	}

}
