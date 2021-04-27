package android.content.res;

import androidx.annotation.NonNull;

import java.util.Collection;

public interface IResourcesLoader {
    void initForFastCompare(@NonNull AssetManager asset, @NonNull Collection<String> resPaths);

    /**
     * with pre inited resPaths
     */
    void loadResources(@NonNull AssetManager asset);
}
