import firebase from 'firebase/app'
import 'firebase/auth'

const firebaseConfig = {
	apiKey: "AIzaSyD04OslM1g2U6m9WhLtmdF3Uv1dN7_xtnk",
	authDomain: "bizbox-acfbf.firebaseapp.com",
	databaseURL: "https://bizbox-acfbf.firebaseio.com",
	projectId: "bizbox-acfbf",
	storageBucket: "bizbox-acfbf.appspot.com",
	messagingSenderId: "624846557914",
	appId: "1:624846557914:web:44627f50c39020db78cbe3",
	measurementId: "G-WKHNV9V91G"
};

firebase.initializeApp(firebaseConfig)
export default {
	loginWithGoogle() {
		let provider = new firebase.auth.GoogleAuthProvider()
		return firebase.auth().signInWithPopup(provider).then(function(result) {
			let accessToken = result.credential.accessToken
			let user = result.user
			return result
		}).catch(function(error){
			console.error('[Google Login Error]', error)
		})
	}
}