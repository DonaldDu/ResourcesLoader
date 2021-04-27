package android.content.res;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * api >= [21,28)
 */
public class ResourcesLoader21 extends BaseResourcesLoader<StringBlock> {
    @Override
    public StringBlock[] getAPKs(@NonNull AssetManager asset) {
        return asset.ensureStringBlocks();
    }

    @Override
    public List<String> getLoadedResDirs(@NonNull AssetManager asset, @NonNull StringBlock[] fullApks) {
        int size = fullApks.length;
        List<String> paths = new ArrayList<>();
        for (int i = 1; i < size; i++) {
            paths.add(asset.getCookieName(i));
            try {
                paths.add(asset.getCookieName(i));
            } catch (Throwable e) {
                e.printStackTrace();
                //some phone like LG and SONY, may occur empty cookie error.
            }
        }
        return paths;
    }
}
