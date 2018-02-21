#include "presenterwindow.h"

PresenterWindow::PresenterWindow(Repository* r,Subject& se,QWidget *parent)
	: QWidget(parent),repo(r),session(se)
{
	ui.setupUi(this);
	this->session.registerObserver(this);
	populateQuestionList(repo->getQuestionsById());
	connectSignalsAndSlots();
}

PresenterWindow::~PresenterWindow()
{

}

void PresenterWindow::populateQuestionList(vector<Question> q)
{
	if (ui.questionList->count() > 0)
		ui.questionList->clear();
	for (auto s : q)
	{
		QString item = QString::fromStdString(s.toStr());
		QListWidgetItem *listItem = new QListWidgetItem{ item };
		ui.questionList->addItem(listItem);
	}
}

void PresenterWindow::connectSignalsAndSlots()
{
	QObject::connect(ui.addButton, SIGNAL(clicked()), this, SLOT(addHandler()));
}

void PresenterWindow::update()
{
	populateQuestionList(repo->getQuestionsById());
}

void PresenterWindow::addHandler()
{
	try
	{
		string id = ui.idLine->text().toStdString();
		string text = ui.textLine->text().toStdString();
		string answer = ui.answerLine->text().toStdString();
		string score = ui.scoreLine->text().toStdString();

		Question q{ stoi(id),text,answer,stoi(score) };

		repo->addQuestion(q);
		this->session.notify();
	}
	catch (StartException& e)
	{
		QMessageBox messageBox;
		messageBox.critical(0, "Error", e.what());
	}
}