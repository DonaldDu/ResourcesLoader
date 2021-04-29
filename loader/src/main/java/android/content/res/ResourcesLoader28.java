package android.content.res;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * api >= 28
 */
public class ResourcesLoader28 extends BaseResourcesLoader<ApkAssets> {
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
