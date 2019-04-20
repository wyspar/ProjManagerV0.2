//change package name after downloading
package com.example.managerv2;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class CreateProjectDialog extends DialogFragment{


    View view;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    private ArrayList<String> globalContacts = new ArrayList<>();
    private ArrayList<String> memberNames = new ArrayList<>();

    public CreateProjectDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {

        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);

         builder = new  AlertDialog.Builder(getActivity())
                .setTitle(R.string.add_project_dialog_title)
                .setPositiveButton(R.string.create_project_confirm_button_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText titleText = (EditText)view.findViewById(R.id.projectTitleInput);
                                EditText descText = (EditText)view.findViewById(R.id.projectDescriptionInput);

                                String title = titleText.getText().toString();
                                String desc = descText.getText().toString();

                                Project p1 = new Project(title, desc, memberNames);
                                MainActivity.projects.add(p1);


                                ListView layout = (ListView) getActivity().findViewById(R.id.listview_projects);
                                layout.invalidate();

                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );

        view = getActivity().getLayoutInflater().inflate(R.layout.create_project_dialog, null);

        final Button button = view.findViewById(R.id.spinnerButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(view.GONE);
                spinnerSetup();
            }
        });

        builder.setView(view);

        dialog = builder.create();

        //forces keyboard to be visible
        //fixes a bug where newly created projects won't be visible
        //in the activity
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return dialog;

    }

    //creates the drop down list for contacts
    private void spinnerSetup(){

        Spinner spinner = view.findViewById(R.id.projPeopleInput);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, getContactNames());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(createSpinnerListener);
        spinner.performClick();

    }

    //import contact names from phone
    private ArrayList<String> getContactNames() {

        ArrayList<String> contacts = new ArrayList<>();
        // Get the ContentResolver
        ContentResolver cr = getActivity().getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // Move the cursor to first element
        if (cursor.moveToFirst()) {
            // Iterate through the cursor
            do {
                // Get the contact names
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contacts.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        globalContacts = contacts;
        return contacts;
    }

    //creates a new textview when the user selects a person from the spinner
    //also removes that person from the spinner so the same person
    //can't be added multiple times
    private AdapterView.OnItemSelectedListener createSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ListIterator<String> iterator = globalContacts.listIterator();
            String name = globalContacts.get(position);
            final ScrollView scroller = dialog.findViewById(R.id.createProjectDialogLayout);
            LinearLayout layout = dialog.findViewById(R.id.scrollChildLayout);
            TextView tv1 = new TextView(dialog.getContext());
            tv1.setText(name);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv1.setLayoutParams(layoutParams);
            memberNames.add(name);

            try{
                scroller.post(new Runnable() {
                    @Override
                    public void run() {
                        scroller.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });

                layout.addView(tv1);
                Toast t1 = Toast.makeText(getContext(), name + " added to project.", Toast.LENGTH_SHORT);
                t1.setGravity(Gravity.BOTTOM,0,5);
                t1.show();
                //globalContacts.remove(item);
            }catch (Exception e){
                e.printStackTrace();
            }

            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}



