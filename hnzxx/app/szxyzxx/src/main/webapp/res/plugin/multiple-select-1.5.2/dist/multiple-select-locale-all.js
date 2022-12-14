(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(require('jquery')) :
  typeof define === 'function' && define.amd ? define(['jquery'], factory) :
  (global = global || self, factory(global.jQuery));
}(this, function ($) { 'use strict';

  $ = $ && $.hasOwnProperty('default') ? $['default'] : $;

  /**
   * Multiple Select en-US translation
   * Author: Zhixin Wen<wenzhixin2010@gmail.com>
   */

  $.fn.multipleSelect.locales['en-US'] = {
    formatSelectAll: function formatSelectAll() {
      return '[Select all]';
    },
    formatAllSelected: function formatAllSelected() {
      return 'All selected';
    },
    formatCountSelected: function formatCountSelected(count, total) {
      return count + ' of ' + total + ' selected';
    },
    formatNoMatchesFound: function formatNoMatchesFound() {
      return 'No matches found';
    }
  };
  $.extend($.fn.multipleSelect.defaults, $.fn.multipleSelect.locales['en-US']);

  /**
   * Multiple Select es-ES translation
   * Author: Zhixin Wen<wenzhixin2010@gmail.com>
   */

  $.fn.multipleSelect.locales['es-ES'] = {
    formatSelectAll: function formatSelectAll() {
      return '[Seleccionar todo]';
    },
    formatAllSelected: function formatAllSelected() {
      return 'Todos seleccionados';
    },
    formatCountSelected: function formatCountSelected(count, total) {
      return count + ' de ' + total + ' seleccionado';
    },
    formatNoMatchesFound: function formatNoMatchesFound() {
      return 'No se encontraron coincidencias';
    }
  };
  $.extend($.fn.multipleSelect.defaults, $.fn.multipleSelect.locales['es-ES']);

  /**
   * Multiple Select it-IT translation
   * Author: Giuseppe Lodi Rizzini
   */

  $.fn.multipleSelect.locales['it-IT'] = {
    formatSelectAll: function formatSelectAll() {
      return '[Seleziona tutti]';
    },
    formatAllSelected: function formatAllSelected() {
      return 'Tutti selezionati';
    },
    formatCountSelected: function formatCountSelected(count, total) {
      return count + ' di ' + total + ' selezionati';
    },
    formatNoMatchesFound: function formatNoMatchesFound() {
      return 'Nessun risultato';
    }
  };
  $.extend($.fn.multipleSelect.defaults, $.fn.multipleSelect.locales['it-IT']);

  /**
   * Multiple Select zh-CN translation
   * Author: Zhixin Wen<wenzhixin2010@gmail.com>
   */

  $.fn.multipleSelect.locales['zh-CN'] = {
    formatSelectAll: function formatSelectAll() {
      return '[??????]';
    },
    formatAllSelected: function formatAllSelected() {
      return '?????????????????????';
    },
    formatCountSelected: function formatCountSelected(count, total) {
      return '??????' + total + '??????????????????' + count + '???';
    },
    formatNoMatchesFound: function formatNoMatchesFound() {
      return '??????????????????';
    }
  };
  $.extend($.fn.multipleSelect.defaults, $.fn.multipleSelect.locales['zh-CN']);

  /**
   * Multiple Select zh-TW translation
   * Author: Zhixin Wen<wenzhixin2010@gmail.com>
   */

  $.fn.multipleSelect.locales['zh-TW'] = {
    formatSelectAll: function formatSelectAll() {
      return '[??????]';
    },
    formatAllSelected: function formatAllSelected() {
      return '?????????????????????';
    },
    formatCountSelected: function formatCountSelected(count, total) {
      return '??????' + total + '??????????????????' + count + '???';
    },
    formatNoMatchesFound: function formatNoMatchesFound() {
      return '??????????????????';
    }
  };
  $.extend($.fn.multipleSelect.defaults, $.fn.multipleSelect.locales['zh-TW']);

}));
