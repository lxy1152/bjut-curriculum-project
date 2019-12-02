# Author：Xiangyu Li
# Email: lxy_jdsy@163.com
# Time：2019/7/1 15:13
# Name: area.py
# Description:
import sys

from PyQt5 import QtCore
from PyQt5.QtCore import QRect, QPoint, Qt
from PyQt5.QtGui import QPalette, QImage
from PyQt5.QtWidgets import QWidget, QRubberBand, QApplication, QMessageBox


class Area(QWidget):
    finish = QtCore.pyqtSignal(QRect)

    def __init__(self):
        super().__init__()
        self.is_cut = True
        self.previous = None
        self.current = None
        self.rect = None
        self.rubber_band = QRubberBand(QRubberBand.Rectangle)
        self.setWindowOpacity(0.5)

    def mousePressEvent(self, event):
        self.previous = event.pos()
        if not self.is_cut:
            self.is_cut = True

    def mouseMoveEvent(self, event):
        if self.is_cut:
            self.current = event.pos()
            self.rect = QRect(self.previous, self.current)
            self.rubber_band.setGeometry(self.rect.normalized())
            self.rubber_band.show()

    def mouseReleaseEvent(self, event):
        if self.is_cut and self.rect is not None:
            self.is_cut = False
            self.setWindowOpacity(0.1)
            self.rubber_band.close()
            choice = QMessageBox.question(self, '提示', '确定选择这块区域吗？', QMessageBox.Yes | QMessageBox.No, QMessageBox.Yes)
            if choice == QMessageBox.Yes:
                self.setWindowOpacity(0.5)
                self.finish.emit(self.rect)
            elif choice == QMessageBox.No:
                self.setWindowOpacity(0.5)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    main_widget = ScreenShot()
    main_widget.showFullScreen()
    # main_widget.show()
    sys.exit(app.exec())
