import java.util.HashSet;

class SocialMediaList {

    class FriendNode {
        String friendID;
        FriendNode next;

        FriendNode(String friendID, FriendNode next) {
            this.friendID = friendID;
            this.next = next;
        }
    }

    class SocialMediaNode {
        String userID;
        String name;
        int age;
        FriendNode friendList;
        SocialMediaNode next;

        SocialMediaNode(String userID, String name, int age) {
            this.userID = userID;
            this.name = name;
            this.age = age;
            this.friendList = null;
            this.next = null;
        }
    }

    private SocialMediaNode head = null;

    public void addNewUser(String userID, String name, int age) {
        SocialMediaNode newUser = new SocialMediaNode(userID, name, age);
        newUser.next = head;
        head = newUser;
    }

    private SocialMediaNode findUser(String userID) {
        SocialMediaNode temp = head;
        while (temp != null) {
            if (temp.userID.equals(userID)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void addFriend(String userID1, String userID2) {
        SocialMediaNode user1 = findUser(userID1);
        SocialMediaNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (!isFriend(user1.friendList, userID2)) {
            user1.friendList = new FriendNode(userID2, user1.friendList);
        }
        if (!isFriend(user2.friendList, userID1)) {
            user2.friendList = new FriendNode(userID1, user2.friendList);
        }
    }

    private boolean isFriend(FriendNode friendList, String friendID) {
        while (friendList != null) {
            if (friendList.friendID.equals(friendID)) return true;
            friendList = friendList.next;
        }
        return false;
    }

    public void removeFriend(String userID1, String userID2) {
        SocialMediaNode user1 = findUser(userID1);
        SocialMediaNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.friendList = removeFriendFromList(user1.friendList, userID2);
        user2.friendList = removeFriendFromList(user2.friendList, userID1);
    }

    private FriendNode removeFriendFromList(FriendNode head, String friendID) {
        if (head == null) return null;
        if (head.friendID.equals(friendID)) return head.next;

        FriendNode temp = head;
        while (temp.next != null) {
            if (temp.next.friendID.equals(friendID)) {
                temp.next = temp.next.next;
                return head;
            }
            temp = temp.next;
        }
        return head;
    }

    public void findMutualFriends(String userID1, String userID2) {
        SocialMediaNode user1 = findUser(userID1);
        SocialMediaNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        HashSet<String> user1Friends = new HashSet<>();
        FriendNode temp = user1.friendList;
        while (temp != null) {
            user1Friends.add(temp.friendID);
            temp = temp.next;
        }

        System.out.print("Mutual Friends: ");
        boolean found = false;
        temp = user2.friendList;
        while (temp != null) {
            if (user1Friends.contains(temp.friendID)) {
                System.out.print(temp.friendID + " ");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.print("None");
        System.out.println();
    }

    public void displayFriends(String userID) {
        SocialMediaNode user = findUser(userID);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print(user.name + "'s Friends: ");
        FriendNode temp = user.friendList;
        if (temp == null) {
            System.out.println("No friends.");
            return;
        }
        while (temp != null) {
            System.out.print(temp.friendID + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void searchUser(String keyword) {
        SocialMediaNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.userID.equals(keyword) || temp.name.equalsIgnoreCase(keyword)) {
                System.out.println("User Found: " + temp.name + " (ID: " + temp.userID + "), Age: " + temp.age);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("User not found.");
        }
    }

    public void countFriends() {
        SocialMediaNode temp = head;
        while (temp != null) {
            int count = 0;
            FriendNode friendTemp = temp.friendList;
            while (friendTemp != null) {
                count++;
                friendTemp = friendTemp.next;
            }
            System.out.println(temp.name + " has " + count + " friend(s).");
            temp = temp.next;
        }
    }

    public void displayAllUsers() {
        SocialMediaNode temp = head;
        if (temp == null) {
            System.out.println("No users found.");
            return;
        }
        System.out.println("All Users:");
        while (temp != null) {
            System.out.println("Name: " + temp.name + " | ID: " + temp.userID + " | Age: " + temp.age);
            temp = temp.next;
        }
    }
}

public class SocialMediaFriendConnections {
    public static void main(String[] args) {
        SocialMediaList list = new SocialMediaList();

        // Adding users
        list.addNewUser("101", "Aman", 25);
        list.addNewUser("102", "Raman", 23);
        list.addNewUser("103", "Amit", 30);


        // Adding friends
        list.addFriend("101", "102");
        list.addFriend("101", "103");
        list.addFriend("102", "103");
        list.addFriend("103", "104");

        // Display all users
        list.displayAllUsers();

        // Display friends of a list
        list.displayFriends("101");
        list.displayFriends("103");

        // Finding mutual friends
        list.findMutualFriends("101", "103");

        // Removing a friend
        list.removeFriend("101", "103");
        System.out.println("\nAfter removing friend:");
        list.displayFriends("101");

        // Searching for a list
        list.searchUser("Raman");
        list.searchUser("101");

        // Counting friends for each list
        list.countFriends();
    }
}

//SampleOutput
//One or both users not found.
//All Users:
//Name: Amit | ID: 103 | Age: 30
//Name: Raman | ID: 102 | Age: 23
//Name: Aman | ID: 101 | Age: 25
//Aman's Friends: 103 102
//Amit's Friends: 102 101
//Mutual Friends: 102
//
//After removing friend:
//Aman's Friends: 102
//User Found: Raman (ID: 102), Age: 23
//User Found: Aman (ID: 101), Age: 25
//Amit has 1 friend(s).
//Raman has 2 friend(s).
//Aman has 1 friend(s).