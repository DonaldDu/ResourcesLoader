package android.content.res;

import androidx.annotation.NonNull;

import java.util.Collection;

public interface IResourcesLoader {
    void initFastCompare(@NonNull AssetManager asset, @NonNull Collection<String> resPaths);

    /**
     * with pre inited resPaths
     */
    void loadResources(@NonNull AssetManager asset);
}
