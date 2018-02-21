#include "Participant.h"



Participant::Participant()
{
}


int Participant::isId(int id)
{
	for (auto i : ids)
		if (id == i)
			return 1;
	return 0;
}

Participant::~Participant()
{
}

istream & operator >> (istream & in, Participant & participant)
{
	string s;
	char *sir, *p;
	getline(in, s);
	int id;
	int score;

	if (s.length() == 0)
		return in;
	sir = (char*)malloc(sizeof(char)*(s.size() + 1));
	strcpy(sir, s.c_str());
	p = strtok(sir, "|");
	string name(p);

	p = strtok(NULL, "|");
	score = stoi(p);
	
	participant.setName(name);
	participant.setScore(score);
	return in;
}
