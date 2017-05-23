require_relative 'tinydsl'

require 'nokogiri'
require 'cgi'
def fmt arr
  a = CGI.unescapeHTML(Nokogiri::XML(arr.join).to_xml(:encoding => "UTF-8"))
  x = a.scan /<\?import.+\?>/
  a.gsub! /<\?import.+\?>\n?/, ''
  a['<?xml version="1.0" encoding="UTF-8"?>'] += "\n" + x.join("\n")
  a
end

puts fmt TinyDSL.new {
  FXScope = %W[ id controller ]
  def initialize
    @imports = []
  end
  def imports
    @imports.uniq
  end
  def _fix s
    s = s.to_s
    FXScope.include?(s) ? 'fx:' + s : s
  end
  def _handle_opts opts = {}
    opts.map { |k, v| " #{_fix k}=#{v.inspect}" }.join
  end
  def anchor_pane_beg opts = {}
    @imports << '<?import javafx.scene.layout.AnchorPane?>'
    [
      "<AnchorPane #{_handle_opts(opts)}>",
        "<children>"
    ]
  end
  def text_field opts = {}
    @imports << '<?import javafx.scene.control.TextField?>'
    "<TextField #{_handle_opts(opts)} />"
  end
  def button opts = {}
    @imports << '<?import javafx.scene.control.Button?>'
    "<Button onAction= #{_handle_opts(opts)} />"
  end
  def anchor_pane_end
    [
        "</children>",
      "</AnchorPane>"
    ]
  end
}.run {
  anchor_pane_beg controller: 'application.Main'
    text_field id: 'textField'
    button onAction: '#onClick'
  anchor_pane_end
  imports
}.result
