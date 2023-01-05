package xyz.mrmelon54.EnhancedSearchability.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;
import xyz.mrmelon54.EnhancedSearchability.config.ConfigStructure;

@OnlyIn(Dist.CLIENT)
public class EnhancedSearchabilityClient {
    private static EnhancedSearchabilityClient instance;
    private ConfigStructure config;

    public EnhancedSearchabilityClient() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInitializeClient);

        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));

    }

    //@Override
    public void onInitializeClient(final FMLClientSetupEvent event) {
        instance = this;

        AutoConfig.register(ConfigStructure.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ConfigStructure.class).getConfig();
    }

    public static EnhancedSearchabilityClient getInstance() {
        return instance;
    }

    public ConfigStructure getConfig() {
        return config;
    }

    public boolean enableServerSearchBar() {
        return getConfig().serversEnabled;
    }

    public boolean enableResourcePackSearchBar() {
        return getConfig().resourcePacksEnabled;
    }

    public boolean enableStatsSearchBar() {
        return getConfig().statsEnabled;
    }
}
