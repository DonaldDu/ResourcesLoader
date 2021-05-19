package android.content.res;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * api >= 28
 */
@SuppressWarnings("NewApi")
public class ResourcesLoader28 extends BaseResourcesLoader<ApkAssets> {
    @Nullable
    private AssetManager sSystem;

    public ResourcesLoader28() {
        super(new ApkAssets[0]);
        try {
            @SuppressWarnings("JavaReflectionMemberAccess")
            Field sSystemF = AssetManager.class.getDeclaredField("sSystem");
            sSystemF.setAccessible(true);
            sSystem = (AssetManager) sSystemF.get(null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initFastCompare(@NonNull AssetManager asset, @NonNull Collection<String> resPaths) {
        super.initFastCompare(asset, resPaths);
        if (sSystem != null && hasResources()) {
            loadResources(sSystem, this.resPaths);
        }
    }

    @Override
    public void loadResources(@NonNull AssetManager asset) {
        if (hasResources()) {
            ApkAssets[] apKs = getAPKs(asset);
            if (fullApks.length != apKs.length) {
                fullApks = loadResources(asset, resPaths);
            }
        }
    }

    @Override
    public ApkAssets[] getAPKs(@NonNull AssetManager asset) {
        return asset.getApkAssets();
    }

    @Override
    public Collection<String> getLoadedResDirs(@NonNull AssetManager asset, @NonNull ApkAssets[] fullApks) {
        List<String> paths = new ArrayList<>(fullApks.length);
        for (ApkAssets apk : fullApks) {
            paths.add(apk.getAssetPath());
        }
        return paths;
    }
}
