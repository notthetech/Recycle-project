#include <stdio.h>

void WasteCategorization() // A checklist to see what to do with it
{
    int choice = 0;
    printf("\n\nWaste Categorization:\n");
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

void WasteSchedule() // check if choice is global or just local in main
{
    int choice = 0;
    printf("\n\nCollection Schedule:\n");
    while (1)
    {
        printf("1. Collection Days\n");
        printf("2. Collection Times\n");
        printf("3. Back to Main Menu\n");
        scanf("%d", &choice);
        switch(choice)
        {
            //code for collection schedule calling from other files
            default:
                printf("Invalid, choose an option\n");
        }
    }
}

void UserTracking()// user tracking, code to create and remove user accounts

{
    //code
}
//maybe add a ranking feature to the user tracking system

void RewardSystem() // Reward System, find user points and redeem points
{
    int choice = 0;
    printf("\n\nReward System:\n");
    while (1)
    {
        printf("1. View Points\n");
        printf("2. Redeem Points\n");
        printf("3. Back to Main Menu\n");
        scanf("%d", &choice);
        switch(choice)
        {
            //code for reward system calling from other files
            default:
                printf("Invalid, choose an option\n");
        }
    }
}

void EduContent() // Educational Content
{
    int choice = 0;
    printf("\n\nEducational Content:\n");
    while (1)
    {
        printf("1. Waste Categories\n");
        printf("2. Recycling Process\n");
        printf("3. Composting\n");
        printf("4. Waste Reduction Tips\n");
        printf("5. Back to Main Menu\n");
        scanf("%d", &choice);
        switch (choice)
        {
            case 1:
                printf("Info");
                break;
            case 2:
                printf("Info");
                break;
            case 3:
                printf("Info");
                break;
            case 4:
                printf("Info");
                break;
            case 5:
                return;
            default:
                printf("Invalid, choose an option\n");
        }
    }
}
