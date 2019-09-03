package reaperz.plugin.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class FileBuilder {

    private File file;
    private YamlConfiguration cfg;

    public FileBuilder(String FilePath, String FileName) {
        this.file = new File(FilePath, FileName);
        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileBuilder setValue(String ValuePath, Object Value) {
        cfg.set(ValuePath, Value);
        return this;
    }

    public boolean exist() {
        return file.exists();
    }

    //cfg - get's
    public int getInt(String ValuePath) {
        return cfg.getInt(ValuePath);
    }

    public String getString(String ValuePath) {
        return cfg.getString(ValuePath);
    }

    public boolean getBoolean(String ValuePath) {
        return cfg.getBoolean(ValuePath);
    }

    public List<String> getStringList(String ValuePath) {
        return cfg.getStringList(ValuePath);
    }

    public double getDouble(String ValuePath) {
        return cfg.getDouble(ValuePath);
    }

    public long getLong(String ValuePath) {
        return cfg.getLong(ValuePath);
    }

    public Set<String> getKeys(boolean deep) {
        return cfg.getKeys(deep);
    }

    public ConfigurationSection getConfigurationSection(String Section) {
        return cfg.getConfigurationSection(Section);
    }

    public FileBuilder save() {
        try {
            this.cfg.save(this.file);
        } catch (IOException e) {
        }
        return this;
    }
}
