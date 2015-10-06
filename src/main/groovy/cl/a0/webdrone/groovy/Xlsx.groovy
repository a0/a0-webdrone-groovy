package cl.a0.webdrone.groovy

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

class Xlsx {
  protected Browser a0
  def filename = 'data.xlsx'
  def sheet = 0
  def dict
  def rows
  def both

  class Params extends Expando {
    def sheet
    def filename

    def propertyMissing(String name) { }
    void propertyMissing(String name, value) { }
  }

  Xlsx(Browser a0) {
    this.a0 = a0
  }

  def dict(Map args) {
    Params params = new Params(args)
    update_sheet_filename(params.sheet, params.filename)
    if (dict == null) {
      reset()
      dict = [:]
      FileInputStream fileInputStream = new FileInputStream(new File(filename))
      Workbook workbook = WorkbookFactory.create(fileInputStream)
      Sheet sheet = sheet in Integer ? workbook.getSheetAt(sheet) : workbook.getSheet(sheet)
      for (Row row : sheet.tail()) {
        def k = cellValue(row, 0)
        def v = cellValue(row, 1)
        dict[k] = v
      }
      fileInputStream.close()
    }
    dict
  }

  def rows(Map args) {
    Params params = new Params(args)
    update_sheet_filename(params.sheet, params.filename)
    if (rows == null) {
      reset()
      FileInputStream fileInputStream = new FileInputStream(new File(filename))
      Workbook workbook = WorkbookFactory.create(fileInputStream)
      Sheet sheet = sheet in Integer ? workbook.getSheetAt(sheet) : workbook.getSheet(sheet)
      rows = sheet.collect { Row row ->
        row.collect { cell ->
          cellValue(cell)
        }
      }
      fileInputStream.close()
    }
    rows
  }

  def both(Map args) {
    Params params = new Params(args)
    update_sheet_filename(params.sheet, params.filename)
    if (both == null) {
      reset()
      FileInputStream fileInputStream = new FileInputStream(new File(filename))
      Workbook workbook = WorkbookFactory.create(fileInputStream)
      Sheet sheet = sheet in Integer ? workbook.getSheetAt(sheet) : workbook.getSheet(sheet)
      def rows = sheet.collect { Row row ->
        row.collect { cell ->
          cellValue(cell)
        }
      }
      List head = rows.first()
      both = rows.tail().collect { row ->
        def dict = [:]
        row.eachWithIndex { def entry, int i ->
          if (head.size() > i && head[i] != null) {
            dict[head[i]] = entry
          }
        }
        dict
      }
      fileInputStream.close()
    }
    both
  }

  def save() {
    save([:])
  }

  def save(Map args) {
    Params params = new Params(args)
    if (params.filename) { this.filename = params.filename }
    if (params.sheet) { this.sheet = params.sheet }
    FileInputStream fileInputStream = new FileInputStream(new File(filename))
    Workbook workbook = WorkbookFactory.create(fileInputStream)
    Sheet sheet = sheet in Integer ? workbook.getSheetAt(sheet) : workbook.getSheet(sheet)
    if (dict != null) {
      for (Row row : sheet.tail()) {
        def k = cellValue(row, 0)
        if (dict.containsKey(k)) {
          setCellValue(row, 1, dict[k])
        }
      }
    } else if (both != null) {
      def rows = sheet.collect { Row row ->
        row.collect { cell ->
          cellValue(cell)
        }
      }
      List head = rows.first()
      both.eachWithIndex { Map entry, int i ->
        i += 1
        entry.each { k, v ->
          int j = head.indexOf(k)
          if (j >= 0) {
            Row r = sheet.getRow(i)
            r = r ?: sheet.createRow(i)
            Cell c = r.getCell(j)
            c = c ?: r.createCell(j)
            setCellValue(c, v)
          }
        }
      }
    } else if (rows != null) {
      rows.eachWithIndex { def row, int i ->
        row.eachWithIndex { def entry, int j ->
          Row r = sheet.getRow(i)
          r = r ?: sheet.createRow(i)
          Cell c = r.getCell(j)
          c = c ?: r.createCell(j)
          setCellValue(c, entry)
        }
      }
    }
    fileInputStream.close()
    FileOutputStream fileOutputStream = new FileOutputStream(filename)
    workbook.write(fileOutputStream)
    fileOutputStream.close()
    reset()
  }

  def reset() {
    this.dict = this.rows = this.both = null
  }

  protected update_sheet_filename(sheet, filename) {
    if (sheet && sheet != this.sheet) {
      this.sheet = sheet
      reset()
    }
    if (filename && filename != this.filename) {
      this.filename = filename
      reset()
    }
  }

  protected cellValue(Cell cell) {
    switch (cell.getCellType()) {
      case Cell.CELL_TYPE_FORMULA:
      case Cell.CELL_TYPE_STRING:
        return cell.getRichStringCellValue().getString()
      case Cell.CELL_TYPE_NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          return cell.getDateCellValue()
        } else {
          return cell.getNumericCellValue()
        }
      case Cell.CELL_TYPE_BOOLEAN:
        return cell.getBooleanCellValue()
      default:
        return ""
    }
  }

  protected cellValue(Row row, int index) {
    try {
      Cell cell = row.getCell(index)
      cellValue(cell)
    } catch (Throwable t) {
    }
  }

  protected setCellValue(Cell cell, value) {
    cell.setCellValue(value)
  }

  protected setCellValue(Row row, int index, value) {
    try {
      Cell cell = row.getCell(index)
      cell = cell ?: row.createCell(index)
      setCellValue(cell, value)
    } catch (Throwable t) {
    }
  }
}
