/********************************************************************************
** Form generated from reading UI file 'trenchcoatsqt.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TRENCHCOATSQT_H
#define UI_TRENCHCOATSQT_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_TrenchCoatsQtClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *TrenchCoatsQtClass)
    {
        if (TrenchCoatsQtClass->objectName().isEmpty())
            TrenchCoatsQtClass->setObjectName(QStringLiteral("TrenchCoatsQtClass"));
        TrenchCoatsQtClass->resize(600, 400);
        menuBar = new QMenuBar(TrenchCoatsQtClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        TrenchCoatsQtClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(TrenchCoatsQtClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        TrenchCoatsQtClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(TrenchCoatsQtClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        TrenchCoatsQtClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(TrenchCoatsQtClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        TrenchCoatsQtClass->setStatusBar(statusBar);

        retranslateUi(TrenchCoatsQtClass);

        QMetaObject::connectSlotsByName(TrenchCoatsQtClass);
    } // setupUi

    void retranslateUi(QMainWindow *TrenchCoatsQtClass)
    {
        TrenchCoatsQtClass->setWindowTitle(QApplication::translate("TrenchCoatsQtClass", "TrenchCoatsQt", 0));
    } // retranslateUi

};

namespace Ui {
    class TrenchCoatsQtClass: public Ui_TrenchCoatsQtClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TRENCHCOATSQT_H
