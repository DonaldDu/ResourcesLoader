package android.content.res;

import android.util.ArraySet;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public abstract class AssetManager {
    private StringBlock mStringBlocks[] = null;
    /**
     * not found for user
     */
    @Deprecated
    private static ApkAssets[] sSystemApkAssets;
    /**
     * not found for user
     */
    @Deprecated
    private static ArraySet<ApkAssets> sSystemApkAssetsSet;

    @RequiresApi(api = 28)
    @NonNull
    public abstract ApkAssets[] getApkAssets();

    @RequiresApi(api = 21)
    public abstract int addAssetPath(String path);

    /**
     * not found for user
     */
    @Deprecated
    public abstract void setApkAssets(@NonNull ApkAssets[] apkAssets, boolean invalidateCaches);

    /**
     * inaccessible for user
     */
    @Deprecated
    @NonNull
    abstract StringBlock[] ensureStringBlocks();

    public native final String getCookieName(int cookie);
}
