package android.content.res;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseResourcesLoader<APK> implements IResourcesLoader {
    @Nullable
    private APK[] fullApks;
    private final Set<String> resPaths = new HashSet<>(0);

    @Override
    public void initForFastCompare(@NonNull AssetManager asset, @NonNull Collection<String> resPaths) {
        if (this.resPaths.containsAll(resPaths)) return;
        this.resPaths.addAll(resPaths);
        fullApks = loadResources(asset, this.resPaths);
    }

    @Override
    public void loadResources(@NonNull AssetManager asset) {
        if (fullApks != null) {
            APK[] apKs = getAPKs(asset);
            if (!Arrays.equals(fullApks, apKs)) {
                fullApks = loadResources(asset, resPaths);
            }
        }
    }

    private APK[] loadResources(@NonNull AssetManager asset, @NonNull Collection<String> resPaths) {
        APK[] apKs = getAPKs(asset);
        List<String> loaded = getLoadedResDirs(asset, apKs);
        List<String> toLoad = new ArrayList<>(resPaths);
        toLoad.removeAll(loaded);
        for (String path : toLoad) {
            asset.addAssetPath(path);
        }
        return getAPKs(asset);
    }

    public abstract APK[] getAPKs(@NonNull AssetManager asset);

    public abstract List<String> getLoadedResDirs(@NonNull AssetManager asset, @NonNull APK[] fullApks);
}
