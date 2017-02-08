package g7.zcf.phoneconnnect;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import g7.zcf.R;

public class PhoneContactActivity extends Activity {

    private static final int PHONE_INFO = 0;

    private TextView mGetConnect;
    private TextView mShowPhoneInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_contact);
        mGetConnect = (TextView) findViewById(R.id.getContact);
        mShowPhoneInfo = (TextView) findViewById(R.id.phoneInfo);
        mGetConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), PHONE_INFO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PHONE_INFO) {
            ContentResolver resolver = getContentResolver();
            Uri contactUri = data.getData();
            Cursor cursor = managedQuery(contactUri, null, null, null, null);
            cursor.moveToFirst();
            String userName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null,
                    null);
            while (phone.moveToNext()) {
                String userNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                mShowPhoneInfo.setText(userName + "\n" +userNumber);
            }
        } else {
            Toast.makeText(PhoneContactActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
        }
    }
}
