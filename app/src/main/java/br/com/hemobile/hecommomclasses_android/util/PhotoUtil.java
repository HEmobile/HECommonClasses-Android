package br.com.hemobile.hecommomclasses_android.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.hemobile.hecommomclasses_android.MyApplication;


public class PhotoUtil {

    public static final int PICK_IMAGE = 9;
    private static final String PHOTO_DIR_NAME = MyApplication.getAppName();

    public static Bitmap resizeBitmap(String pathName) {
        Options opts = new Options();
        opts.inSampleSize = 1;
        opts.inJustDecodeBounds = true;
        Bitmap bitmap = null;
        boolean rightSize = false;
        while (!rightSize) {
            opts.inSampleSize = opts.inSampleSize * 2;
            bitmap = BitmapFactory.decodeFile(pathName, opts);
            rightSize = opts.outHeight < 1000 && opts.outWidth < 2048;
        }
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(pathName, opts);
        return bitmap;
    }

    public static Bitmap resizeBitmap(Activity actv, Uri uriImage) {
        try {
            Options opts = new Options();
            opts.inSampleSize = 1;
            opts.inJustDecodeBounds = true;
            Bitmap bitmap = null;
            boolean rightSize = false;
            while (!rightSize) {
                opts.inSampleSize = opts.inSampleSize * 2;
                bitmap = BitmapFactory.decodeStream(actv.getContentResolver().openInputStream(uriImage), null, opts);
                rightSize = opts.outHeight < 1000 && opts.outWidth < 2048;
            }
            opts.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeStream(actv.getContentResolver().openInputStream(uriImage), null, opts);
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getBytes(Bitmap bmp) {

        return getBytes(bmp, 100);
    }

    public static byte[] getBytes(Bitmap bmp, int quality) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        return stream.toByteArray();
    }

    public static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    @SuppressLint("SimpleDateFormat")
    public static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), PHOTO_DIR_NAME);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
        return mediaFile;
    }

    public static void showPhotoOnGallery(Context c, String path) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        File file = new File(path);
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            i.setDataAndType(uri, "image/jpeg");
            c.startActivity(i);
        } else {
            String msg;
            if (path.contains("/")) {
                msg = "Esta foto já não está mais armazenada no seu aparelho.";
            } else {
                msg = "Baixando...";
            }
            Toast.makeText(c, msg, Toast.LENGTH_LONG).show();
        }
    }

    public static void setImageOnView(ImageView imageView, String path, int resNotFound, int resDownloading) {
        File file = new File(path);
        if (file.exists()) {
            imageView.setImageBitmap(resizeBitmap(path));
        } else {
            if (path.contains("/")) {
                imageView.setImageResource(resNotFound);
            } else {
                imageView.setImageResource(resDownloading);
            }
        }
    }

    public static void getImageFromGallery(Activity actv) {
        if (actv == null) return;
        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_PICK);
//        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        actv.startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), PICK_IMAGE);
    }

    public static void getFragmentImageFromGallery(Fragment actv) {
        if (actv == null) return;
        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_PICK);
//        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        actv.startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), PICK_IMAGE);
    }

    public static Uri onGalleryResult(int requestCode, Intent data) {
        if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            Uri _uri = data.getData();
            return _uri;
        }
        return null;
    }

    public static Bitmap getBitmapFromCameraData(Intent data, Context context) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return BitmapFactory.decodeFile(picturePath);
    }


}