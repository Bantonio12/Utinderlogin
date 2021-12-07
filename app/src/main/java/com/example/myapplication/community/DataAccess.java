package com.example.myapplication.community;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class DataAccess implements DataAccessI{

    HashMap postList;

//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    DocumentReference postListRef = db.document("community/Posts");
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference postListRef = db.document("community/Posts");

    @Override
    public HashMap getData() {

        postListRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    HashMap newPostList = (HashMap) document.get("PostList");
                    postList = newPostList;

                    System.out.println("null --------------------------- null");
                    System.out.println(postList.toString());
                }
            }
        });
        return postList;
    }

    @Override
    public void updateData(HashMap newPost) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference postListRef = db.document("community/Post");
        postListRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    HashMap postList = (HashMap) document.get("PostList");

                    postList.put(newPost.get("title").toString(), newPost);

                    HashMap newData = new HashMap();

                    newData.put("PostList", newData);

                    postListRef.set(newData);
                }
            }
        });
    }

}
