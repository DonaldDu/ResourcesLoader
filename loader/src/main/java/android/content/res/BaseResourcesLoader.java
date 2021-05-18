package android.content.res;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseResourcesLoader<APK> implements IResourcesLoader {
    protected final List<String> resPaths = new ArrayList<>();

    @CallSuper
    @Override
    public void initForFastCompare(@NonNull AssetManager asset, @NonNull Collection<String> resPaths) {
        if (this.resPaths.containsAll(resPaths)) return;
        this.resPaths.removeAll(resPaths);
        this.resPaths.addAll(resPaths);
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
