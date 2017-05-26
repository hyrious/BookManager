#!/usr/bin/env ruby -wKU
puts %Q{[Info] Classes: #{
Dir.glob(File.join(ARGV[0], '**/*.class')).map { |file|
  file.gsub(/#{ARGV[0]}|\.class/, '').split('/').reject(&:empty?).join('.')
}.join(' ')
}}
