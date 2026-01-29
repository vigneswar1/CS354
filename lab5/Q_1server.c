#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define MSG_SIZE 256

struct msgbuf {
    long mtype;
    char text[MSG_SIZE];
};

int main() {
    key_t key = ftok("msgqueue", 65);
    int msqid = msgget(key, 0666 | IPC_CREAT);

    struct msgbuf msg;

    // Priority 1 (highest)
    msg.mtype = 1;
    strcpy(msg.text, "High priority message");
    msgsnd(msqid, &msg, sizeof(msg.text), 0);

    // Priority 3 (lower)
    msg.mtype = 3;
    strcpy(msg.text, "Low priority message");
    msgsnd(msqid, &msg, sizeof(msg.text), 0);

    // Priority 2 (medium)
    msg.mtype = 2;
    strcpy(msg.text, "Medium priority message");
    msgsnd(msqid, &msg, sizeof(msg.text), 0);

    printf("Messages sent.\n");
    return 0;
}
