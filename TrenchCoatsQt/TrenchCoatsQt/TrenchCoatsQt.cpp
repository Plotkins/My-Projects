#include "TrenchCoatsQt.h"
#include <string>
#include <QMessageBox>
#include <stdexcept>
#include <cstdlib>
#include <qgroupbox.h>
#include <algorithm>
#include "RepositoryExceptions.h"

TrenchCoatsQt::TrenchCoatsQt(Controller & c, QWidget * parent) : ctrl{ c }, QWidget{ parent }
{
	this->initGUI();
	this->currentCoatsInRepo = this->ctrl.getRepo().getCoats();
	this->populateRepoList();
}

TrenchCoatsQt::~TrenchCoatsQt()
{
}

void TrenchCoatsQt::initGUI()
{
	QHBoxLayout* layout = new QHBoxLayout{ this };

	QWidget* leftWidget = new QWidget{};
	QVBoxLayout* leftSide = new QVBoxLayout{ leftWidget };

	/*QWidget* labactivityWidget = new QWidget{};
	QGroupBox* boxLayout = new QGroupBox{ labactivityWidget };
	this->orderButton = new QRadioButton("Order");
	this->shuffleButton = new QRadioButton("Shuffle");*/
	

	// list
	this->repoList = new QListWidget{};
	// set the selection model
	this->repoList->setSelectionMode(QAbstractItemView::SingleSelection);

	QWidget* coatDataWidget = new QWidget{};
	QFormLayout* formLayout = new QFormLayout{ coatDataWidget };
	this->colourEdit = new QLineEdit{};
	this->sizeEdit = new QLineEdit{};
	this->priceEdit = new QLineEdit{};
	this->quantityEdit = new QLineEdit{};
	this->lengthEdit = new QLineEdit{};
	this->linkEdit = new QLineEdit{};
	formLayout->addRow("&Colour:", colourEdit);
	formLayout->addRow("&Size:", sizeEdit);
	formLayout->addRow("&Price:", priceEdit);
	formLayout->addRow("&Quantity:", quantityEdit);
	formLayout->addRow("&Length:", lengthEdit);
	formLayout->addRow("&Link:", linkEdit);

	QWidget* buttonsWidget = new QWidget{};
	QGridLayout* gridLayout = new QGridLayout{ buttonsWidget };
	this->addButton = new QPushButton("Add");
	this->deleteButton = new QPushButton("Delete");
	this->updateButton = new QPushButton("Update");
	this->filterButton = new QPushButton("Filter");

	gridLayout->addWidget(addButton, 0, 0);
	gridLayout->addWidget(deleteButton, 0, 1);
	gridLayout->addWidget(updateButton, 0, 2);
	gridLayout->addWidget(filterButton, 0, 3);

	QWidget* activityWidget = new QWidget{};
	QGridLayout* activityLayout = new QGridLayout{ activityWidget };
	this->orderButton = new QRadioButton("Ordered");
	this->shuffleButton = new QRadioButton("Shuffled");

	activityLayout->addWidget(orderButton, 0, 0);
	activityLayout->addWidget(shuffleButton, 0, 1);

	leftSide->addWidget(new QLabel{ "All coats" });
	leftSide->addWidget(activityWidget);
	leftSide->addWidget(repoList);
	leftSide->addWidget(coatDataWidget);
	leftSide->addWidget(buttonsWidget);

	QWidget* middleWidget = new QWidget{};
	QVBoxLayout* vLayoutMiddle = new QVBoxLayout{ middleWidget };
	this->moveOneCoatButton = new QPushButton{ ">> Move one coat" };
	QWidget* Part = new QWidget{};
	QVBoxLayout* vLayoutUpperPart = new QVBoxLayout{ Part };
	vLayoutUpperPart->addWidget(this->moveOneCoatButton);
	vLayoutMiddle->addWidget(Part);

	QWidget* rightWidget = new QWidget{};
	QVBoxLayout* rightSide = new QVBoxLayout{ rightWidget };

	shoppingList = new QListWidget{};

	QWidget* totalSum = new QWidget{};
	QFormLayout* formLayout2 = new QFormLayout{ totalSum };
	totalSumEdit = new QLineEdit{};
	this->totalSumEdit->setText(QString::fromStdString(std::to_string(this->ctrl.getBasket().getPriceSum())));
	formLayout2->addRow("&Total sum:",totalSumEdit);

	QWidget* shoppinglistButtonsWidget = new QWidget{};
	QHBoxLayout* shoppinglistButtonsLayout = new QHBoxLayout{ shoppinglistButtonsWidget };
	this->openImageButton = new QPushButton("Show image");
	shoppinglistButtonsLayout->addWidget(openImageButton,0,0);



	// add everything to the right layout
	rightSide->addWidget(new QLabel{ "Shopping Basket" });
	rightSide->addWidget(shoppingList);
	rightSide->addWidget(totalSum);
	rightSide->addWidget(shoppinglistButtonsWidget);
	
	// add the three layouts to the main layout
	layout->addWidget(leftWidget);
	layout->addWidget(middleWidget);
	layout->addWidget(rightWidget);

	// connect the signals and slots
	this->connectSignalsAndSlots();
}

void TrenchCoatsQt::connectSignalsAndSlots()
{
	QObject::connect(this->repoList, SIGNAL(itemSelectionChanged()), this, SLOT(listItemChanged()));

	// add button connections
	QObject::connect(this->addButton, SIGNAL(clicked()), this, SLOT(addNewCoat()));
	QObject::connect(this->deleteButton, SIGNAL(clicked()), this, SLOT(deleteCoat()));
	QObject::connect(this->updateButton, SIGNAL(clicked()), this, SLOT(updateCoat()));
	QObject::connect(this->filterButton, SIGNAL(clicked()), this, SLOT(filterRepoCoats()));

	QObject::connect(this->moveOneCoatButton, SIGNAL(clicked()), this, SLOT(moveCoatToBasket()));
	QObject::connect(this->moveAllCoatsButton, SIGNAL(clicked()), this, SLOT(moveAllCoats()));

	QObject::connect(this->openImageButton, SIGNAL(clicked()), this, SLOT(openImage()));

	QObject::connect(this->orderButton, SIGNAL(clicked()), this, SLOT(order()));
	QObject::connect(this->shuffleButton, SIGNAL(clicked()), this, SLOT(shuffle()));
}

void TrenchCoatsQt::populateRepoList()
{
	// clear the list, if there are elements in it
	if (this->repoList->count() > 0)
		this->repoList->clear();

	for (auto s : this->currentCoatsInRepo)
	{
		QString itemInList = QString::fromStdString(s.getColour() + " - " + s.getSize());
		QListWidgetItem *item3 = new QListWidgetItem(itemInList);
		//QFont font("Courier", 20, 10, true);
		//item3->setFont(font);
		this->repoList->addItem(item3);
	}

	// set the selection on the first item in the list
	if (this->currentCoatsInRepo.size() > 0)
		this->repoList->setCurrentRow(0);
}

void TrenchCoatsQt::populateShoppingList()
{
	if (this->shoppingList->count() > 0)
		this->shoppingList->clear();

	for (auto s : this->ctrl.getBasket().getItems())
	{
		QString itemInList = QString::fromStdString(s.getColour() + " - " + s.getSize());
		this->shoppingList->addItem(itemInList);
	}
	this->totalSumEdit->setText(QString::fromStdString(std::to_string(this->ctrl.getBasket().getPriceSum())));
}

int TrenchCoatsQt::getRepoListSelectedIndex()
{
	if (this->repoList->count() == 0)
		return -1;

	// get selected index
	QModelIndexList index = this->repoList->selectionModel()->selectedIndexes();
	if (index.size() == 0)
	{
		this->colourEdit->clear();
		this->sizeEdit->clear();
		this->priceEdit->clear();
		this->quantityEdit->clear();
		this->lengthEdit->clear();
		this->linkEdit->clear();
		return -1;
	}

	int idx = index.at(0).row();
	this->oldColour = this->currentCoatsInRepo[idx].getColour();
	this->oldSize = this->currentCoatsInRepo[idx].getSize();
	return idx;
}

void TrenchCoatsQt::addNewCoat()
{
	std::string colour = this->colourEdit->text().toStdString();
	std::string size = this->sizeEdit->text().toStdString();
	std::string price = this->priceEdit->text().toStdString();
	std::string quantity = this->quantityEdit->text().toStdString();
	std::string length = this->lengthEdit->text().toStdString();
	std::string link = this->linkEdit->text().toStdString();
	try
	{
		float p = (float)stod(price);
		float l = (float)stod(quantity);
		this->ctrl.addCoatToRepoC(size, colour, p, 0, l, link);
		this->currentCoatsInRepo = this->ctrl.getRepo().getCoats();
		this->populateRepoList();
	}
	catch (DuplicateSongException& e)
	{
		QMessageBox messageBox;
		messageBox.critical(0, "Error", "The trench coat already exists!");
		return;
	}
	
}

void TrenchCoatsQt::deleteCoat()
{
	std::string colour = this->colourEdit->text().toStdString();
	std::string size = this->sizeEdit->text().toStdString();
	this->ctrl.deleteCoatC(size, colour);
	this->currentCoatsInRepo=this->ctrl.getRepo().getCoats();
	this->populateRepoList();
}

void TrenchCoatsQt::updateCoat()
{
	std::string colour = this->colourEdit->text().toStdString();
	std::string size = this->sizeEdit->text().toStdString();
	std::string price = this->priceEdit->text().toStdString();
	std::string quantity = this->quantityEdit->text().toStdString();
	std::string length = this->lengthEdit->text().toStdString();
	std::string link = this->linkEdit->text().toStdString();

	float p = (float)stod(price);
	float l = (float)stod(quantity);
	this->ctrl.updateCoatC(oldSize,oldColour,size, colour, p, 0, l, link);
	this->currentCoatsInRepo = this->ctrl.getRepo().getCoats();
	this->populateRepoList();
}

void TrenchCoatsQt::filterRepoCoats()
{
	std::string size = this->sizeEdit->text().toStdString();
	this->currentCoatsInRepo = this->ctrl.getSuitableCoats(size);
	this->populateRepoList();
}

void TrenchCoatsQt::moveCoatToBasket()
{
	int idx = this->getRepoListSelectedIndex();
	if (idx == -1)
		return;

	std::vector<Coat> songs = this->currentCoatsInRepo;

	// get the song at the selected index
	if (idx >= songs.size())
		return;
	Coat s = songs[idx];

	this->ctrl.addCoatToBasket(s);
	this->currentCoatsInBasket = this->ctrl.getBasket().getItems();
	this->currentCoatsInRepo[idx].substractQuantity();
	this->populateRepoList();
	this->populateShoppingList();
}

void TrenchCoatsQt::openImage()
{
	int idx = this->getRepoListSelectedIndex();
	if (idx == -1)
		return;

	std::vector<Coat> songs = this->currentCoatsInRepo;

	// get the song at the selected index
	if (idx >= songs.size())
		return;
	Coat s = songs[idx];
	s.openImg();
}

void TrenchCoatsQt::shuffle()
{
	std::random_shuffle(this->currentCoatsInRepo.begin(), currentCoatsInRepo.end());
	this->orderButton->setChecked(false);
	this->populateRepoList();
}

void TrenchCoatsQt::order()
{
	//std::sort(this->currentCoatsInRepo.begin(), currentCoatsInRepo.end());
	int i, j;
	Coat aux;
	for (unsigned int i = 0; i < this->currentCoatsInRepo.size()-1; i++)
		for (unsigned int j = i+1;j  < this->currentCoatsInRepo.size(); j++)
			if (this->currentCoatsInRepo[i].getLength() > this->currentCoatsInRepo[j].getLength())
			{
				aux = this->currentCoatsInRepo[i];
				this->currentCoatsInRepo[i] = this->currentCoatsInRepo[j];
				this->currentCoatsInRepo[j] = aux;
			}
	this->shuffleButton->setChecked(false);
	this->populateRepoList();
}
