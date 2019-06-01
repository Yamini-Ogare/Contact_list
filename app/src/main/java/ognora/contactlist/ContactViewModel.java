package ognora.contactlist;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ContactViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<ContactModel>> arrayList = new MutableLiveData<>();
    ContactModel contactModel ;
    ArrayList<ContactModel> contactlist =new ArrayList<>() ;
    ContentResolver cr = getApplication().getContentResolver() ;


    public ContactViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<ArrayList<ContactModel>> getContacts(ProgressBar progressBar) {


       AsyncTaskRunner asyncTaskRunner = (AsyncTaskRunner) new AsyncTaskRunner(progressBar).execute();

        return arrayList;
    }


    private class AsyncTaskRunner extends AsyncTask<Void, Void, Void> {

         ProgressBar bar ;

        public AsyncTaskRunner(ProgressBar progressBar) {
            this.bar = progressBar;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Cursor cn = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null
                    /*new String[]{ContactsContract.Contacts.DISPLAY_NAME}*/,
                    null, null, null);
           /* Cursor cp = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    new String[]{ContactsContract.Contacts._ID},
                    null, null, null);*/

           String[] from = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone._ID} ;

            String sr[] = cn.getColumnNames() ;



            for (cn.moveToFirst(); !cn.isAfterLast(); cn.moveToNext()){

                    contactModel = new ContactModel(cn.getString(cn.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),cn.getString(cn.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                contactlist.add(contactModel);
                }

               // contactlist.add(contactModel);




            return null;

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            arrayList.setValue(contactlist);

            bar.setVisibility(View.GONE);

        }
    }





}
