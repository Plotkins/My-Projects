/********************************************************************************
** Form generated from reading UI file 'quizwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_QUIZWINDOW_H
#define UI_QUIZWINDOW_H

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

class Ui_QuizWindowClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *QuizWindowClass)
    {
        if (QuizWindowClass->objectName().isEmpty())
            QuizWindowClass->setObjectName(QStringLiteral("QuizWindowClass"));
        QuizWindowClass->resize(600, 400);
        menuBar = new QMenuBar(QuizWindowClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        QuizWindowClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(QuizWindowClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        QuizWindowClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(QuizWindowClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        QuizWindowClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(QuizWindowClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        QuizWindowClass->setStatusBar(statusBar);

        retranslateUi(QuizWindowClass);

        QMetaObject::connectSlotsByName(QuizWindowClass);
    } // setupUi

    void retranslateUi(QMainWindow *QuizWindowClass)
    {
        QuizWindowClass->setWindowTitle(QApplication::translate("QuizWindowClass", "QuizWindow", 0));
    } // retranslateUi

};

namespace Ui {
    class QuizWindowClass: public Ui_QuizWindowClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_QUIZWINDOW_H
