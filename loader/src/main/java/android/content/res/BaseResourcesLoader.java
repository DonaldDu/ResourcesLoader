package android.content.res;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseResourcesLoader<APK> implements IResourcesLoader {
    protected final Set<String> resPaths = new HashSet<>();
    @NonNull
    protected APK[] fullApks;

    /**
     * @param emptyApks empty array
     */
    public BaseResourcesLoader(@NonNull APK[] emptyApks) {
        this.fullApks = emptyApks;
    }

    @Override
    public synchronized void initFastCompare(@NonNull AssetManager asset, @NonNull Collection<String> resPaths) {
        if (this.resPaths.containsAll(resPaths)) return;
        this.resPaths.addAll(resPaths);
        fullApks = loadResources(asset, this.resPaths);
    }

    protected boolean hasResources() {
        return resPaths.size() > 0;
    }

    protected APK[] loadResources(@NonNull AssetManager asset, @NonNull Collection<String> resPaths) {
        APK[] apKs = getAPKs(asset);
        Collection<String> loaded = getLoadedResDirs(asset, apKs);
        List<String> toLoad = new ArrayList<>(resPaths);
        toLoad.removeAll(loaded);
        for (String path : toLoad) {
            asset.addAssetPath(path);
        }
        return getAPKs(asset);
    }

    public abstract APK[] getAPKs(@NonNull AssetManager asset);

    public abstract Collection<String> getLoadedResDirs(@NonNull AssetManager asset, @NonNull APK[] fullApks);
}
