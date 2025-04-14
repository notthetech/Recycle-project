#include <stdio.h>
#include <string.h>
//when finished with a function just commit the changes and push to github
 struct Account
{
    int points = 0;
    char name[20] = {""};
    char password[20] = {""};
    struct chedule Schedule;
    typedef struct TrashDays
};
struct Day
{
    
};
struct Schedule
{
    struct Day Days[7] 
};





void WasteCategorization() // A checklist to see what to do with it
{
    int choice = 0;
    printf("\n\nWaste Categor

Account Accounts[] = {};ization:\n");
    printf("Describe the waste:\n");
    while(1)
    {
        printf("1. Organic\n");
        printf("2. Inorganic\n");
        printf("3. Back to Main Menu\n");
        scanf("%d", &choice);
        if (choice == 1)
        {
            printf("Organic waste includes food scraps, yard waste, and paper products.\n");
            printf("Look at your label and compost if possible.\n");
        }
        else if (choice == 2)
        {
            printf("Inorganic waste includes plastics, metals, and glass.\n");
            printf("Look at your label and recycle if possible.\n");
        }
        else if (choice == 3)
        {
            return;
        }
        else
        {
            printf("Invalid choice, please try again.\n");
        }
    }
}
//an array to store the days trash is delivered


void WasteSchedule(struct Account user) // gives info based on times. 
{
    
    int choice = 0;
    printf("\n\nCollection Schedule:\n");
    while (1)
    {
        for(int i =  0; i < 6; ii++)
        {
            for()
        }
        printf("1. Collection Schedule\n");
        printf("2. Print Schedule\n");
        printf("3. Back to Main Menu\n");
        scanf("%d", &choice);
        switch(choice)
        {
            case(1)
            {
                printf("Enter Trash days");
                scanf("%s", &);
                
                printf("Enter the time of pick up");
                scanf("%c", &);
                    
            }
            case(2)
            {
                printf("Your collection date: %c %c",)
            }
            case(3)
            {
                return;
            }
            //code for collection schedule calling from other files
            default:
                printf("Invalid, choose an option\n");
        }
    }
}

void UserTracking()// has the user make all the accounts at once and then use them in the other functions
{
    int UserCap = 0;// sets limit so easier to manage
    printf("Welcome to the Waste Management System!\n");
    printf("User Management");
    printf("Enter the max number of users");
    scanf("%d", &UserCap);
    ptr = (struct UserAccount*)malloc(UserCap * sizeof(struct UserAccounnt));
    for (int i = 0; i < UserCap; i++)
    {
        printf("Enter your name: ");
        scanf("%s", (ptr+i)->name);
        printf("Enter your password: ");
        scanf("%s", (ptr+i)->password);
        printf("Enter your points: ");
        scanf("%d", (ptr+i)->points);
    }
    //code for loop to add data using max users to determing for how long
}
//maybe add a ranking feature to the user tracking system

void VerifyUser() //checks if user and password match
{
    char login_name[20];
    char login_password[20];
    printf("Enter your name: ");
    fgets(login_name, sizeof(login_name), stdin);
    if (strcmp(login_name, ptr->name) == 0)
    {
        printf("Enter your password: ");
        fgets(login_password,sizeof(login_password), stdin);;
        if (strcmp(login_password, ptr->password) == 0)
        {
            printf("Login successful!\n");
            return 1;
        }
        else
        {
            printf("Invalid password.\n");
            return 0;
        }
    }
    else
    {
        printf("Invalid name.\n");
        return 0;
    }
}

void RewardSystem() // Reward System, find user points and redeem points
{
    int choice = 0;
    printf("\n\nReward System:\n");
    while (1)
    {
        printf("1. View Points\n");
        printf("2. View Ranking\n");
        printf("3. Back to Main Menu\n");
        scanf("%d", &choice);
        switch(choice)
        {
            case 1:
                printf("You have %d points.\n", ptr->points);
                break;
            case 2:
                ViewRanking();
                break; 
            default:
                printf("Invalid, choose an option\n");
        }
    }
}

void ViewRanking()
{
    //sort all users (for loop using cap)
    //find current users placement (second for loop to find ranking)
    //print out the ranking
    printf("Ranking:\n");
    
}rawe
void EduContent() // Educational Content

{
    int choice = 0;
    printf("\n\nEducational Content:\n");
    while (1)
    {
        printf("1. Recycling Categories\n");
        printf("2. Recycling Process\n");
        printf("3. Composting\n");
        printf("4. Waste Reduction Tips\n");
        printf("5. Back to Main Menu\n");
        scanf("%d", &choice);
        switch (choice)
        {
            case 1:
                printf("Organic\nPaper\nPlasic\nGlass\nMetal\nE-Waste\nMixed\n");
                break;
            case 2:
                printf("Info\n");
                break;
            case 3:
                printf("Info\n");
                break;
            case 4:
                printf("Info\n");
                break;
            case 5:
                return;
            default:
                printf("Invalid, choose an option\n");
        }
    }
}
