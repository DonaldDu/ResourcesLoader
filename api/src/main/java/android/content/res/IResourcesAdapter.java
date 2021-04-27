package android.content.res;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IResourcesAdapter {
    void installSplitResDirs(AssetManager asset, List<String> splitResPaths) throws Throwable;

    List<String> getLoadedResourcesDirs(AssetManager asset) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException;
}
