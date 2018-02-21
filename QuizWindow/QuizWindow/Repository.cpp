#include "Repository.h"
#include "Exceptions.h"


void Repository::readFromFile()
{
	ifstream f(Qfilename);
	Question q;
	while (f >> q)
	{
		questions.push_back(q);
	}
	f.close();

	ifstream r(Pfilename);
	Participant p;
	while (r >> p)
	{
		Participant* pointer = new Participant{ p.getName() };
		participants.push_back(pointer);
	}
	r.close();
}

void Repository::writeToFile()
{
	ofstream g(Qfilename);
	if (g.is_open())
	{
		for (auto i : questions)
		{
			g << i.toLine();
		}
	}
	g.close();
}

Repository::Repository()
{
}

Repository::Repository(string Qf,string Pf)
{
	Qfilename = Qf;
	Pfilename = Pf;
	readFromFile();
}

vector<Question> Repository::getQuestionsByScore()
{
	vector<Question> v = questions;
	unsigned int i, j;
	Question aux;
	for(i=0;i<v.size()-1;i++)
		for(j=i+1;j<v.size();j++)
			if (v[i].getScore() < v[j].getScore())
			{
				aux = v[i];
				v[i] = v[j];
				v[j] = aux;
			}
	return v;
}

vector<Question> Repository::getQuestionsById()
{
	vector<Question> v = questions;
	unsigned int i, j;
	Question aux;
	for (i = 0; i<v.size() - 1; i++)
		for (j = i + 1; j<v.size(); j++)
			if (v[i].getId() > v[j].getId())
			{
				aux = v[i];
				v[i] = v[j];
				v[j] = aux;
			}
	return v;
}

/*
Description: adds a question object to repository
pre:
	q- question that need to be added
post:
	the question is added to questions vector
throw:
	exception if :id is invalid
					text is empty
*/
void Repository::addQuestion(Question q)
{
	int pos = findPosOfQustion(q);
	if (pos != -1)
		throw StartException("There is anoter question with this ID.");
	if(q.getText()=="")
		throw StartException("The question text is empty.");
	questions.push_back(q);
}

/*
Description:adds the score to participant total
score and the question id to id list of participant
pre:
	p- participant pointer
	score - score to be added
	id -id to be added
post:
	the total score of participant is upadated
*/
void Repository::addScoreToParticipant(Participant * p, int score,int id)
{
	int newScore = score + p->getScore();
	p->setScore(newScore);
	p->addId(id);
}


unsigned int Repository::findPosOfQustion(Question s)
{
	unsigned int pos = -1, i;
	for (i = 0; i<questions.size(); i++)
		if (questions[i].getId() == s.getId())
		{
			pos = i;
			break;
		}
	return pos;
}

Repository::~Repository()
{
	writeToFile();
}
