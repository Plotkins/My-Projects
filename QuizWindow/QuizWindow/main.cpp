#include "quizwindow.h"
#include <QtWidgets/QApplication>
#include "participantwindow.h"
#include "presenterwindow.h"
#include "Participant.h"
#include "Tests.h"
int main(int argc, char *argv[])
{
	Tests::runTests();
	QApplication a(argc, argv);
	Subject session;
	Repository* repo = new Repository{ "questions.txt", "participants.txt" };
	vector<Participant*> part = repo->getParticipants();
	for (auto i : part)
	{
		ParticipantWindow* w1 = new ParticipantWindow{ i,repo,session };
		w1->show();
	}
	PresenterWindow w2{ repo,session };
	w2.show();
	return a.exec();
}
