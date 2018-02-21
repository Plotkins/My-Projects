#pragma once
#include <string>
#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
class Participant
{
private:
	string name;
	int score;
	vector<int> ids;
public:
	Participant();
	Participant(string n):name(n),score(0){}

	string getName() { return name; }
	int getScore() { return score; }

	void setName(string n) { name = n; }
	void setScore(int s) { score = s; }

	void addId(int id) { ids.push_back(id); }
	int isId(int id);
	~Participant();
};
istream & operator >> (istream & in, Participant & p);

