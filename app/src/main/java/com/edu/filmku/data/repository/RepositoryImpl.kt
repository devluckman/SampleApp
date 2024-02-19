package com.edu.filmku.data.repository

import com.edu.filmku.data.network.Resource
import com.edu.filmku.domain.model.UserModel
import com.edu.filmku.domain.repository.Repository
import com.edu.filmku.domain.request.RequestLogin
import com.edu.filmku.domain.request.RequestRegister
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *
 * Created by Lukmanul Hakim on  17/02/24
 * devs.lukman@gmail.com
 */
class RepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : Repository {
    override val isLogged: Flow<Boolean>
        get() = flow {
            delay(2000)

            emit(firebaseAuth.currentUser != null)
        }

    override fun loginWithEmailPassword(request: RequestLogin, callback: (Resource<Unit>) -> Unit) {
        callback(Resource.Loading())
        firebaseAuth.signInWithEmailAndPassword(request.email, request.password)
            .addOnSuccessListener {
                callback(Resource.Success(Unit))
            }.addOnFailureListener {
                it.printStackTrace()
                callback(Resource.Error("${it.message}"))
            }
    }

    override fun registerWithEmailPassword(
        request: RequestRegister,
        callback: (Resource<Unit>) -> Unit
    ) {
        callback(Resource.Loading())
        firebaseAuth.createUserWithEmailAndPassword(request.email, request.password)
            .addOnSuccessListener { auth ->
                auth.user?.let {

                    val userData = UserModel(it.uid, request.email, request.name)
                    Firebase.firestore.collection("user")
                        .document(it.uid)
                        .set(userData).addOnSuccessListener {
                            callback.invoke(Resource.Success(Unit))
                        }
                }
            }.addOnFailureListener {
                it.printStackTrace()
                callback(Resource.Error("${it.message}"))
            }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}