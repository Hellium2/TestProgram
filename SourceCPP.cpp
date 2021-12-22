#include "NativeCalls.h"
#include <string>
#include <iostream>

using namespace std;

class Data {

	struct Record{
		string state;
		string city;
		int population;
	};

	Record *records;



public:
	void newMap(const char **states, const char **cities, int *populations, int count) {
		
		records = new Record[count];
		for (int i = 0; i < count; i++) {
			records[i].state = states[i];
			records[i].city = cities[i];
			records[i].population = populations[i];
		}		
	}

	int PopulationInState(string state, int count) {
		int sum = 0;
		for (int i = 0; i < count; i++) {
			if (records[i].state == state) {
				sum += records[i].population;
			}
		}
		return sum;
	}


};



JNIEXPORT jint JNICALL Java_com_company_NativeCalls_CalculatePopulationSum
  (JNIEnv *env, jclass myclass, jobjectArray states, jobjectArray cities, jintArray populations, jint count, jstring state) {


	const char **st = new const char*[count];
	const char **cit = new const char*[count];
	jobject buf;
	jstring strbuf;
	
	for (int i = 0; i < count; i++) {
		buf = (env) -> GetObjectArrayElement(states, i);
		strbuf = (jstring)buf;
		st[i] = (env) -> GetStringUTFChars(strbuf, 0);
	}

	for (int i = 0; i < count; i++) {
		buf = (env)->GetObjectArrayElement(states, i);
		strbuf = (jstring)buf;
		cit[i] = (env)->GetStringUTFChars(strbuf, 0);
	}

	int sum = 0;
	jint* intbuf = new jint[count];
	(env)->GetIntArrayRegion(populations, 0, count, intbuf);
	int *pop = new int[count];
	for (int i = 0; i<count; i++) {
		pop[i] = intbuf[i];
		sum = sum + intbuf[i];
	}

	Data data;
	data.newMap(st, cit, pop, count);

	return data.PopulationInState((env)->GetStringUTFChars(state, 0), count);

}

