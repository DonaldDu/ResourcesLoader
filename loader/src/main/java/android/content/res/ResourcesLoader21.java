package android.content.res;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * api >= [21,28)
 */
public class ResourcesLoader21 extends BaseResourcesLoader<Object> {
    private final Field mStringBlocksF;

    public ResourcesLoader21() {
        try {
            mStringBlocksF = AssetManager.class.getDeclaredField("mStringBlocks");
            mStringBlocksF.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Object[] getAPKs(@NonNull AssetManager asset) {
        try {
            return (Object[]) mStringBlocksF.get(asset);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public Collection<String> getLoadedResDirs(@NonNull AssetManager asset, @NonNull Object[] fullApks) {
        List<String> paths = new ArrayList<>(fullApks.length);
        int size = fullApks.length;
        for (int i = 0; i < size; i++) {
            try {
                paths.add(asset.getCookieName(i + 1));
            } catch (Throwable e) {
                e.printStackTrace();
                //some phone like LG and SONY, may occur empty cookie error.
            }
        }
        return paths;
    }
}
