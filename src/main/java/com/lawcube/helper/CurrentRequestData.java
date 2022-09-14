package com.lawcube.helper;

public class CurrentRequestData {
	private static ThreadLocal<String> currentUser = new ThreadLocal<>();

    public static void setCurrentUser(String email) {
    	currentUser.set(email);
    }

    public static String getCurrentUser() {
        return currentUser.get();
    }
    public static void clear() {
    	currentUser.remove();
    }
}

