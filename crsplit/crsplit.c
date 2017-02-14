#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>

#define EXEC_DIR_NAME "/exec"
#define DATA_DIR_NAME "/data"
#define WORK_DIR_NAME "/.work"
#define LIVE_DIR_NAME "/live"

#define EXEC_DIR_FLAGS (S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH)
#define DATA_DIR_FLAGS (S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH)
#define WORK_DIR_FLAGS (S_IRWXU)
#define LIVE_DIR_FLAGS (S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH)

#define MOUNT_CMD "mount -t overlay overlay -o lowerdir=%s,upperdir=%s,workdir=%s,nosuid,nodev %s"

char* mallocconcat(char* a, char* b) {

	int len = strlen(a) + strlen(b) + 1;
	char* out = malloc(len);

	strcat(out, a);
	strcat(out, b);

	return out;

}

void makeextdir(char* base, char* ext, int flags) {

	char* fullname = mallocconcat(base, ext);
	printf("create: %s\n", fullname);
	if (mkdir(fullname, flags) != 0 && errno != EEXIST) {
		perror("mkdir");
	}

	free(fullname);

}

char* mallocfullpath(char* base, char* ext) {

	char* full = mallocconcat(base, ext);
	char* actualpath = malloc(PATH_MAX + 1);
	realpath(full, actualpath);
	free(full);

	return actualpath;

}

int main(int argc, char* argv[]) {

	if (argc != 2) {
		printf("usage: %s <path>\n", argv[0]);
		return -1;
	}

	if (getuid() != 0) {

		printf("error: must be root\n");
		return -1;

	}

	char* splitpath = argv[1];

	if (mkdir(splitpath, 0755) != 0 && errno != EEXIST) {
		perror("mkdir");
		return -1;
	}

	makeextdir(splitpath, EXEC_DIR_NAME, EXEC_DIR_FLAGS);
	makeextdir(splitpath, DATA_DIR_NAME, DATA_DIR_FLAGS);
	makeextdir(splitpath, WORK_DIR_NAME, WORK_DIR_FLAGS);
	makeextdir(splitpath, LIVE_DIR_NAME, LIVE_DIR_FLAGS);

	char* lowerpath = mallocfullpath(splitpath, EXEC_DIR_NAME);
	char* upperpath = mallocfullpath(splitpath, DATA_DIR_NAME);
	char* workpath = mallocfullpath(splitpath, WORK_DIR_NAME);
	char* mountpath = mallocfullpath(splitpath, LIVE_DIR_NAME);

	char* cmd = malloc(1000000);

	sprintf(cmd, MOUNT_CMD, lowerpath, upperpath, workpath, mountpath);
	printf("mount: %s\n", mountpath);
	system(cmd);

	free(lowerpath);
	free(upperpath);
	free(workpath);
	free(mountpath);
	free(cmd);

}
